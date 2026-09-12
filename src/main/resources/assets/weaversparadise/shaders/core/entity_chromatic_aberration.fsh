#version 150

#moj_import <fog.glsl>

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

uniform float GameTime;

in float vertexDistance;
in vec4 vertexColor;
in vec4 lightMapColor;
in vec4 overlayColor;
in vec2 texCoord0;

out vec4 fragColor;

void main() {
    float pulse = 0.5 + 0.5 * sin(GameTime * 100.0);
    float aberrationStrength = pulse * 0.009;

    vec2 offset = vec2(aberrationStrength, 0.0);

    vec4 r = texture(Sampler0, texCoord0 + offset);
    vec4 g = texture(Sampler0, texCoord0);
    vec4 b = texture(Sampler0, texCoord0 - offset);

    vec4 color = vec4(r.r, g.g, b.b, (r.a + g.a + b.a) / 3.0);

    if (color.a < 0.1) {
        discard;
    }

    color *= vertexColor * ColorModulator;
    color.rgb = mix(overlayColor.rgb, color.rgb, overlayColor.a);
    color *= lightMapColor;
    fragColor = linear_fog(color, vertexDistance, FogStart, FogEnd, FogColor);
}