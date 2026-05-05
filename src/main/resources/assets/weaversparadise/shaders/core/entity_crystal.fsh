#version 150

#moj_import <fog.glsl>

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;
uniform float FogStart;
uniform float FogEnd;
uniform vec4 FogColor;

uniform float Scale;
uniform vec2 Offset;
uniform vec3 Angle;
uniform float Seed;
uniform float UVMix;
uniform int NthClosest;
uniform float Time;
uniform float Speed;

in float vertexDistance;
in vec4 vertexColor;
in vec4 lightMapColor;
in vec4 overlayColor;
in vec2 texCoord0;

out vec4 fragColor;

float hash(vec3 p3){
    p3 = fract(p3 * 0.1031);
    p3 += dot(p3,p3.yzx + 19.19);
    return fract((p3.x + p3.y) * p3.z);
}

vec2 offsetCell(vec2 cell) {
    return cell + vec2(
    hash(vec3(cell.x, Seed + 1.234, cell.y)) - 0.5,
    hash(vec3(cell.y, -cell.x, Seed + 4.321)) - 0.5
    ) * Offset;
}

vec2 voronoiNoise(vec2 value){
    vec2 center = floor(value);

    vec3 values[9];

    for(int x = -1; x <= 1; x++) {
        int i = (x+1)*3 + 1;
        for (int y = -1; y <= 1; y++) {
            vec2 cellPos = center + vec2(x,y);
            values[y+i] = vec3(cellPos, length(offsetCell(cellPos) - value));
        }
    }

    float maxFloat = 3.402823466e+38;
    float closestDistance = maxFloat;
    int closestIndex = 4;

    for (int i = 0; i < min(NthClosest, 9); i++) {
        for (int j = 0; j < 9; j++) {
            if (values[j].z < closestDistance) {
                closestIndex = j;
                closestDistance = values[j].z;
            }
        }
        values[closestIndex].z = maxFloat;
        closestDistance = maxFloat;
    }

    return values[closestIndex].xy;
}

vec4 safeSample(sampler2D s, vec2 uv) {
    vec2 cuv = clamp(uv, vec2(0.0), vec2(1.0));
    return texture(s, cuv);
}

void main() {
    vec4 base = texture(Sampler0, texCoord0);

    if (base.a < 0.02) {
        discard;
    }

    vec2 timeOffset = vec2(
        sin(Time * Speed * 0.37 + Seed * 1.3),
        cos(Time * Speed * 0.23 - Seed * 2.1)
    ) * 0.25;

    vec2 pos = texCoord0 * Scale
        + vec2(
        Time * Speed * 0.35,
        sin(Time * Speed * 0.5) * 0.75
    );

    vec2 cell = voronoiNoise(pos);

    vec2 vorUV = cell / Scale;

    vec2 sampleUV = mix(texCoord0, vorUV, UVMix);

    sampleUV = clamp(sampleUV, vec2(0.0), vec2(1.0));

    vec4 crystalSample = texture(Sampler0, sampleUV);

    bool fallback = (crystalSample.a < 0.05) || (dot(crystalSample.rgb, vec3(0.3333)) < 0.02);

    vec3 crystal = fallback ? base.rgb : crystalSample.rgb;

    vec2 slope = (vec2(
    hash(vec3(cell.x, cell.y, Seed)) - 0.5,
    hash(vec3(Seed, cell.x, cell.y)) - 0.5
    ) * Angle.xy) * pos;

    float angleVal = fract(slope.x + slope.y);

    crystal = mix(crystal, step(angleVal, crystal * crystal), Angle.z);

    vec3 finalColor = mix(base.rgb, crystal, 1);

    vec4 color = vec4(finalColor, base.a);

    color *= vertexColor * ColorModulator;
    color.rgb = mix(overlayColor.rgb, color.rgb, overlayColor.a);
    color *= lightMapColor;

    fragColor = linear_fog(color, vertexDistance, FogStart, FogEnd, FogColor);
}