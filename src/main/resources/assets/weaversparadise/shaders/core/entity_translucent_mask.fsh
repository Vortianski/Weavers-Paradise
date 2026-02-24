#version 150

#moj_import <fog.glsl>

uniform sampler2D Sampler0;
uniform sampler2D Sampler3;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

in float vertexDistance;
in vec4 vertexColor;
in vec4 lightMapColor;
in vec4 overlayColor;
in vec2 texCoord0;

out vec4 fragColor;

void main() {
    vec4 baseColor = texture(Sampler0, texCoord0);
    vec4 maskColor = texture(Sampler3, texCoord0);

    if (baseColor.a < 0.1) {
        discard;
    }

    baseColor.rgb *= maskColor.rgb;

    baseColor *= vertexColor * ColorModulator;
    baseColor.rgb = mix(overlayColor.rgb, baseColor.rgb, overlayColor.a);
    baseColor *= lightMapColor;

    fragColor = linear_fog(baseColor, vertexDistance, FogStart, FogEnd, FogColor);
}
