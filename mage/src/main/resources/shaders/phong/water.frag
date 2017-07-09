#version 330

uniform sampler2D uRefraction;
uniform sampler2D uReflection;

in vec4 refractPos;
in vec4 reflectPos;
in vec3 vViewPath;


out vec4 outColor;

void main(void) 
{
	//Calculo da refração
    vec2 refractCoords = vec2(    
        refractPos.x / refractPos.w / 2.0 + 0.5,
        refractPos.y /refractPos.w / 2.0 + 0.5
    );      
    vec4 refractColor = texture(uRefraction, refractCoords);

	//Calculo do reflexo    
    vec2 reflectCoords = vec2(    
        reflectPos.x / reflectPos.w / 2.0 + 0.5,
        reflectPos.y /reflectPos.w / 2.0 + 0.5
    );        
    vec4 reflexColor = texture(uReflection, reflectCoords);
    
    //Calculo do fresnel
    vec3 V = normalize(vViewPath);
	vec3 N = vec3(0,1,0);
    float fresnel = dot(V, N);

	//Calculo final da cor    
    outColor = mix(reflexColor, refractColor, fresnel);
}