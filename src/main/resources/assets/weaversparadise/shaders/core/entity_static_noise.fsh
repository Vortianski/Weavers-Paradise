#version 150

uniform sampler2D Sampler0;
uniform float GameTime;
uniform int StaticLayers;

in vec4 texProj0;
in vec2 texCoord0;
in vec2 texCoord1;
out vec4 fragColor;

float hash(ivec2 p) {
    int n = p.x + p.y * 57;
    n = (n << 13) ^ n;
    return float((n * (n * n * 15731 + 789221) + 1376312589) & 0x7fffffff) / float(0x7fffffff);
}

mat4 static_layer(float layer) {
    float t = GameTime * 0.5;
    vec2 translation = vec2(17.0 / (layer + 1.0), (2.0 + layer * 0.5) * t);
    float rotation = radians((layer * layer * 4321.0 + layer * 9.0) * t);
    float scale = (4.5 - layer * 0.25) * 2.0;

    mat4 trans = mat4(1.0);
    trans[3].xy = translation;

    mat2 rot = mat2(cos(rotation), -sin(rotation), sin(rotation), cos(rotation));
    mat2 sca = mat2(scale);

    mat4 transform = mat4(sca * rot) * trans;

    const mat4 SCALE_TRANSLATE = mat4(
    0.5, 0.0, 0.0, 0.5,
    0.0, 0.5, 0.0, 0.5,
    0.0, 0.0, 1.0, 0.0,
    0.0, 0.0, 0.0, 1.0
    );

    return SCALE_TRANSLATE * transform;
}

void main() {
    vec4 baseColor = texture(Sampler0, texCoord0);

    if (baseColor.a < 0.1) {
        discard;
    }

    vec4 baseCoord = texProj0;

    float totalNoise = 0.0;

    float timeHashSeed = fract(GameTime * 100.0);

    for (int i = 0; i < StaticLayers; i++) {
        float layer = float(i);

        mat4 layerMat = static_layer(layer);
        vec4 transformedCoord = layerMat * baseCoord;

        vec2 uv = transformedCoord.xy;

        float scale = 256.0;
        ivec2 cell = ivec2(floor(uv * scale));

        int seed = i * 12345 + int(timeHashSeed * 10000.0);
        ivec2 hashedCell = ivec2(cell.x + seed, cell.y + seed * 67);

        float noise = hash(hashedCell);
        totalNoise += noise;
    }

    float intensity = totalNoise / float(StaticLayers);
    fragColor = vec4(vec3(intensity), 1.0);
}