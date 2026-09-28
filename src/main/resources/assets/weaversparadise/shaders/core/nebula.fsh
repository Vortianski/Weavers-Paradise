#version 150

uniform sampler2D Sampler0;
uniform float GameTime;

in vec4 texProj0;
in vec2 texCoord0;
in vec2 texCoord1;

out vec4 fragColor;

float hash(vec2 p) {
    return fract(sin(dot(p, vec2(127.1, 311.7))) * 43758.5453);
}

float noise(vec2 p) {
    vec2 i = floor(p);
    vec2 f = fract(p);
    vec2 u = f * f * (3.0 - 2.0 * f);

    float a = hash(i);
    float b = hash(i + vec2(1.0, 0.0));
    float c = hash(i + vec2(0.0, 1.0));
    float d = hash(i + vec2(1.0, 1.0));

    return mix(mix(a, b, u.x), mix(c, d, u.x), u.y);
}

float fbm(vec2 p, int octaves) {
    float value = 0.0;
    float amp = 0.5;
    float freq = 1.0;
    for (int i = 0; i < octaves; i++) {
        value += amp * noise(p * freq);
        amp *= 0.5;
        freq *= 2.0;
    }
    return value;
}

vec3 nebulaColor(float density, float hueShift) {
    vec3 darkPurple = vec3(0.15, 0.05, 0.25);
    vec3 purple     = vec3(0.55, 0.25, 0.65);
    vec3 blue       = vec3(0.25, 0.45, 0.85);
    vec3 cyan       = vec3(0.30, 0.75, 0.80);
    vec3 pink       = vec3(0.90, 0.45, 0.70);

    float t = density;
    float wave = sin(t * 6.28 + hueShift) * 0.5 + 0.5;

    vec3 color = mix(darkPurple, purple, t);
    color = mix(color, blue, wave * 0.7);
    color = mix(color, pink, (1.0 - t) * 0.5);
    color += cyan * (1.0 - abs(t - 0.5)) * 0.3;

    return color;
}

void main() {
    vec4 armorTex = texture(Sampler0, texCoord0);
    float mask = max(armorTex.r, max(armorTex.g, armorTex.b));

    if (mask < 0.05) discard;

    vec2 uv = texCoord0 * 4.0;
    float time = GameTime;

    vec2 uv1 = uv * 0.8 + vec2(time * 0.05, time * 0.03);
    float n1 = fbm(uv1, 3);

    vec2 uv2 = uv * 1.5 + vec2(-time * 0.12, time * 0.08);
    float n2 = fbm(uv2, 3);

    vec2 uv3 = uv * 3.0 + vec2(time * 0.3, time * 0.2);
    float n3 = fbm(uv3, 2);

    float density = n1 * 0.6 + n2 * 0.3 + n3 * 0.1;
    density = clamp(density * 1.2, 0.0, 1.0);

    float hueShift = n1 * 4.0 + n2 * 2.0 + n3 * 1.0;

    vec3 color = nebulaColor(density, hueShift);

    vec2 starUV = texCoord0 * 200.0;
    vec2 cell = floor(starUV);
    vec2 local = fract(starUV);

    float seed = hash(cell);

    float starProb = 0.005;
    float starMask = step(1.0 - starProb, seed);

    vec2 starPos = vec2(hash(cell + 1.3), hash(cell + 7.1));

    float d = length(local - starPos);

    float starRadius = 0.08 + hash(cell + 3.14) * 0.07;
    float starShape = smoothstep(starRadius, 0.0, d);

    float twinkle = sin(GameTime * 2.0 + seed * 50.0) * 0.5 + 0.5;

    float starBrightness = starMask * starShape * (0.4 + 0.6 * twinkle);

    float hue = fract(seed * 10.0);
    vec3 tint = mix(vec3(1.0,0.8,0.6), vec3(0.7,0.8,1.0), hue);

    color += tint * starBrightness;

    fragColor = vec4(color * mask, 1.0);
}