package jade;


import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

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

    private float[] vertexArray = {
            // Position         // Color
             0.5f,   -0.5f,     0.0f,         1.0f, 0.0f, 0.0f, 1.0f,    // Bottom right    0
            -0.5f,    0.5f,     0.0f,         0.0f, 1.0f, 0.0f, 1.0f,    // Top left        1
             0.5f,    0.5f,     0.0f,         0.0f, 0.0f, 1.0f, 1.0f,    // Top right       2
            -0.5f,   -0.5f,     0.0f,         1.0f, 1.0f, 0.0f, 1.0f,    // Bottom left     3
    };

    private int[] elementArray = {
            2, 1, 0, // Top right triangle
            0, 1, 3  // Bottom left triangle
    };

    public LevelEditorScene() {
//        System.out.println("Inside Level Editor scene");
    }

    // vertexArrayObjectID, vertexBufferObjectID, elementBufferObjectID
    private int vaoID, vboID, eboID;

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

        success = glGetProgrami(shaderProgram, GL_LINK_STATUS);

        if (success == GL_FALSE) {
            int len = glGetProgrami(shaderProgram, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: 'defaultShader.glsl'\n" +
                    "\tLinking of shaders failed");
            System.out.println(glGetProgramInfoLog(shaderProgram, len));
            assert false : "";
        }

//       Generate VAO, VBO, and EBO buffer objects, and send to GPU

        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        // Create a float buffer of vertices
        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();

        // Create VBO, upload the vertex buffer
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        // Create the indices and upload
        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        // Add the vertex attribute pointers
        int positionsSize = 3;
        int colorsSize = 4;
        int floatSizeBytes = 4;
        int vertexSizeInBytes = (positionsSize + colorsSize) * floatSizeBytes;

        glVertexAttribPointer(0, positionsSize, GL_FLOAT,
                false, vertexSizeInBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorsSize, GL_FLOAT,
                false, vertexSizeInBytes, positionsSize * floatSizeBytes);
        glEnableVertexAttribArray(1);

    }

    @Override
    public void update(float dt) {
        // Bind shader program
        glUseProgram(shaderProgram);

        // Bind the VAO tha we're using
        glBindVertexArray(vaoID);

        // Enable vertex attribute pointers
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);

        glUseProgram(0);
    }
}


