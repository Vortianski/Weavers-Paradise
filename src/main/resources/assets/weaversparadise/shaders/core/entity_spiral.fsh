#version 150

#moj_import <fog.glsl>

uniform sampler2D Sampler0;
uniform sampler2D Sampler2;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;
uniform float GameTime;

uniform vec2 ScreenSize;

uniform float SpiralSpeed;
uniform float SpiralScale;
uniform float SpiralRotation;
uniform float SpiralIntensity;
uniform vec3 SpiralDarkColor;
uniform vec3 SpiralBrightColor;

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

    vec2 uv = texProj0.xy / texProj0.w;
    uv = uv * 2.0 - 1.0;

    float aspect = ScreenSize.x / ScreenSize.y;
    uv.x *= aspect;

    float r = length(uv);
    float angle = atan(uv.y, uv.x);

    float depth = log(r + 0.001);

    float time = GameTime * SpiralSpeed;

    float spiral = sin(
        depth * SpiralScale +
        angle * 8.0 +
        SpiralRotation -
        time
    );

    float mask = smoothstep(-0.2, 0.2, spiral);

    vec3 tunnelColor = mix(SpiralDarkColor, SpiralBrightColor, mask);

    baseColor.rgb = mix(baseColor.rgb, tunnelColor, mask * SpiralIntensity);

    baseColor *= vertexColor * ColorModulator;
    baseColor *= lightMapColor;

    fragColor = linear_fog(
        baseColor,
        vertexDistance,
        FogStart,
        FogEnd,
        FogColor
    );
}