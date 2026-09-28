#version 150

#moj_import <fog.glsl>

uniform sampler2D Sampler0;
uniform sampler2D Sampler3;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;
uniform float GameTime;
uniform vec2 ParallaxSpeed;
uniform float ParallaxRotationSpeed;
uniform float ParallaxRotation;
uniform float ParallaxScale;

in float vertexDistance;
in vec4 vertexColor;
in vec4 lightMapColor;
in vec4 overlayColor;
in vec2 texCoord0;
in vec4 texProj0;

out vec4 fragColor;

void main() {
    vec4 baseColor = texture(Sampler0, texCoord0);

    if (baseColor.a < 0.1) {
        discard;
    }

    vec2 screenUV = texProj0.xy / texProj0.w;

    screenUV *= ParallaxScale;

    float angle = ParallaxRotation + GameTime * ParallaxRotationSpeed;
    float s = sin(angle);
    float c = cos(angle);
    screenUV = mat2(c, -s, s, c) * screenUV;

    screenUV += ParallaxSpeed * GameTime;

    vec4 parallaxColor = texture(Sampler3, screenUV);

    baseColor.rgb += parallaxColor.rgb * parallaxColor.a;

    baseColor *= vertexColor * ColorModulator;
    baseColor.rgb = mix(overlayColor.rgb, baseColor.rgb, overlayColor.a);
    baseColor *= lightMapColor;

    fragColor = linear_fog(baseColor, vertexDistance, FogStart, FogEnd, FogColor);
}