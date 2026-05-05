#version 150

#moj_import <fog.glsl>

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

in float vertexDistance;
in vec4 vertexColor;
in vec4 lightMapColor;
in vec4 overlayColor;
in vec2 texCoord0;
in vec3 fragViewPos;
in float vertDist;

out vec4 fragColor;

// RGB to HSV conversion
vec3 rgbToHsv(vec3 c) {
    vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);
    vec4 p = mix(vec4(c.bg, K.wz), vec4(c.gb, K.xy), step(c.b, c.g));
    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));

    float d = q.x - min(q.w, q.y);
    float e = 1.0e-10;
    return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);
}

// HSV to RGB conversion
vec3 hsvToRgb(vec3 c) {
    vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);
    vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);
    return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);
}

void main() {
    vec4 color = texture(Sampler0, texCoord0) * vertexColor * ColorModulator;

    // alpha cutout like vanilla translucent
    if (color.a < 0.1) {
        discard;
    }

    // --------------------------
    // FOIL EFFECT (view-space)
    // --------------------------
    // viewDir: from fragment toward camera (camera = origin in view-space)
    vec3 viewDir = normalize(-fragViewPos);

    // Build a fake normal from the view-space position to get a smooth gradient across the quad
    // Adjust the multiplier for fragViewPos.xy to change the gradient strength
    vec3 fakeNormal = normalize(vec3(fragViewPos.xy * 0.5, 1.0));

    // angle factor: how much hue shift to apply (0..1)
    float angleFactor = max(dot(normalize(fakeNormal), viewDir), 0.0);
    angleFactor = pow(angleFactor, 0.6); // gamma: tune for sharper/softer foil

    // Hue shift amount - adjust this value to change hue shift amount
    float hueShiftAmount = 1; // 0.5 = 180° shift
    // Apply hue shift based on angle factor
    vec3 originalColor = color.rgb;
    vec3 hsv = rgbToHsv(originalColor);
    hsv.x = fract(hsv.x + angleFactor * hueShiftAmount); // Hue rotation
    vec3 hueShiftedColor = hsvToRgb(hsv);

    // Mix between original and hue-shifted color
    color.rgb = mix(originalColor, hueShiftedColor, angleFactor);

    // --------------------------
    // Finish usual pipeline (lighting/overlay/fog)
    // --------------------------
    color.rgb = mix(overlayColor.rgb, color.rgb, overlayColor.a);
    color *= lightMapColor;

    fragColor = linear_fog(color, vertexDistance, FogStart, FogEnd, FogColor);
}