package jade;


import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;

public class LevelEditorScene extends Scene {

//    private boolean changingScene = false;
//    private float timeToChangeScene = 2f;

    private String vertexShaderSource = """
            #version 330

            layout (location=0) in vec3 aPos;
            layout (location=1) in vec4 aColor;

            out vec4 fColor;

            void main()
            {
                fColor = aColor;
                gl_Position = vec4(aPos, 1.0);
            }

            """;

    private String fragmentShaderSource = """
            #version 330

            in vec4 fColor;

            out vec4 color;

            void main()
            {
                color = fColor;
            }

            """;

    private int vertexID, fragmentID, shaderProgram;

    public LevelEditorScene() {
//        System.out.println("Inside Level Editor scene");
    }

    @Override
    public void init() {
        // ========================
        // Compile and link Shaders
        // ========================

        // First load and compile the vertex shader
        vertexID = glCreateShader(GL_VERTEX_SHADER);

        // Pass the shader source to the GPU
        glShaderSource(vertexID, vertexShaderSource);
        glCompileShader(vertexID);

        // Check for errors in compilation
        int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: 'defaultShader.glsl'\n\tVertex shader compilation failed.");
            System.out.println(glGetShaderInfoLog(vertexID, len));
            assert false : "";
        }

        // First load and compile the fragment shader
        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);

        // Pass the shader source to the GPU
        glShaderSource(fragmentID, fragmentShaderSource);
        glCompileShader(fragmentID);

        // Check for errors in compilation
        success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if (success != GL_TRUE) {
            int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: 'defaultShader.glsl'\n\tFragment shader compilation failed.");
            System.out.println(glGetShaderInfoLog(fragmentID, len));
            assert false : "";
        }

//         Link shaders and check for errors
        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexID);
        glAttachShader(shaderProgram, fragmentID);
        glLinkProgram(shaderProgram);

        success = glGetProgrami(shaderProgram, GL_COMPILE_STATUS);

        if (success == GL_FALSE) {
            int len = glGetProgrami(shaderProgram, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: 'defaultShader.glsl'\n" +
                    "\tLinking of shaders failed");
            System.out.println(glGetProgramInfoLog(shaderProgram, len));
            assert false : "";
        }
    }

    @Override
    public void update(float dt) {

//        System.out.printf("%f FPS%n", 1f/dt);
//
//        if (!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
//            changingScene = true;
//        }
//
//        if (changingScene && timeToChangeScene > 0) {
//            timeToChangeScene -= dt;
//            Window.get().r -= dt * 5f;
//            Window.get().g -= dt * 5f;
//            Window.get().b -= dt * 5f;
//        } else if (changingScene) {
//            Window.changeScene(1);
//        }



    }
}


