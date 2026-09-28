#version 150

#moj_import <projection.glsl>

in vec3 Position;
in vec2 UV0;
in vec2 UV1;

uniform mat4 ModelViewMat;
uniform mat4 ProjMat;

out vec4 texProj0;
out vec2 texCoord0;
out vec2 texCoord1;

void main() {
    gl_Position = ProjMat * ModelViewMat * vec4(Position, 1.0);
    texProj0 = projection_from_position(gl_Position);
    texCoord0 = UV0;
    texCoord1 = UV1;
}