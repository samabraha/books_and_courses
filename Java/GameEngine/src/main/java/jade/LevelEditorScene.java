package jade;


import org.lwjgl.BufferUtils;
import render.Shader;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

public class LevelEditorScene extends Scene {
    private int vertexID, fragmentID, shaderProgram;

    private final float[] vertexArray = {
            // Position         // Color
             0.5f,   -0.5f,     0.0f,         1.0f, 0.0f, 0.0f, 1.0f,    // Bottom right    0
            -0.5f,    0.5f,     0.0f,         0.0f, 1.0f, 0.0f, 1.0f,    // Top left        1
             0.5f,    0.5f,     0.0f,         0.0f, 0.0f, 1.0f, 1.0f,    // Top right       2
            -0.5f,   -0.5f,     0.0f,         1.0f, 1.0f, 0.0f, 1.0f,    // Bottom left     3
    };

    private final int[] elementArray = {
            2, 1, 0, // Top right triangle
            0, 1, 3  // Bottom left triangle
    };

    // vertexArrayObjectID, vertexBufferObjectID, elementBufferObjectID
    private int vaoID, vboID, eboID;

    private Shader defaultShader;

    public LevelEditorScene() {
//        System.out.println("Inside Level Editor scene");
    }


    @Override
    public void init() {
        defaultShader = new Shader("assets/shaders/default.glsl");
        defaultShader.compile();

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
        defaultShader.use();

        // Bind the VAO tha we're using
        glBindVertexArray(vaoID);

        // Enable vertex attribute pointers
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);

        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);

        glBindVertexArray(0);

        defaultShader.detach();
    }
}


