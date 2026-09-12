#version 150

#moj_import <fog.glsl>

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;
uniform vec3 Light0_Direction;

in float vertexDistance;
in vec4 vertexColor;
in vec4 lightMapColor;
in vec4 overlayColor;
in vec2 texCoord0;
in vec3 fragViewPos;
in vec3 viewNormal;
in vec3 localPos;

out vec4 fragColor;

vec3 rgbToHsv(vec3 c) {
    vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);
    vec4 p = mix(vec4(c.bg, K.wz), vec4(c.gb, K.xy), step(c.b, c.g));
    vec4 q = mix(vec4(p.xyw, c.r), vec4(c.r, p.yzx), step(p.x, c.r));
    float d = q.x - min(q.w, q.y);
    float e = 1.0e-10;
    return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);
}

vec3 hsvToRgb(vec3 c) {
    vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);
    vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);
    return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);
}

void main() {
    vec4 color = texture(Sampler0, texCoord0) * vertexColor * ColorModulator;

    if (color.a < 0.1) {
        discard;
    }

    vec3 normal = normalize(viewNormal);
    vec3 viewDir = normalize(-fragViewPos);

    vec3 fakeNormal = normalize(vec3(fragViewPos.xy * 0.5, 1.0));
    float camFactor = max(dot(fakeNormal, viewDir), 0.0);
    camFactor = pow(camFactor, 0.6);

    float ndotv = clamp(dot(normal, viewDir), 0.0, 1.0);
    float fresnel = pow(1.0 - ndotv, 2.5);

    float lightDirFactor = clamp(dot(normal, normalize(Light0_Direction)), 0.0, 1.0);
    float luminance = dot(lightMapColor.rgb, vec3(0.299, 0.587, 0.114));
    float lightStrength = clamp(lightDirFactor * 0.5 + luminance * 0.5 + 0.3, 0.0, 1.0);

    float spatialPhase = dot(localPos, vec3(0.35, 0.35, 0.35));

    float effectIntensity = 0.6;
    float angleFactor = clamp(camFactor * 0.75 + fresnel * 0.25, 0.0, 1.0) * lightStrength * effectIntensity;

    float hueShiftAmount = 2;
    vec3 originalColor = color.rgb;
    vec3 hsv = rgbToHsv(originalColor);
    hsv.x = fract(hsv.x + angleFactor * hueShiftAmount + spatialPhase);
    vec3 hueShiftedColor = hsvToRgb(hsv);

    color.rgb = mix(originalColor, hueShiftedColor, angleFactor);

    color.rgb = mix(overlayColor.rgb, color.rgb, overlayColor.a);
    color *= lightMapColor;

    fragColor = linear_fog(color, vertexDistance, FogStart, FogEnd, FogColor);
}