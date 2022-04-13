package jade;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {
    private Matrix4f projectionMatrix, viewMatrix;
    private Vector2f position;

    public Camera(Vector2f position) {
        this.position = position;
        projectionMatrix = new Matrix4f();
        viewMatrix = new Matrix4f();
    }

    public void adjustProjection() {
//        projectionMatrix.identity();
        projectionMatrix.setOrtho(0f, 32f * 40f, 0f, 32f * 21f, 0f, 100f);
    }

    public Matrix4f getViewMatrix() {
        var cameraFront = new Vector3f(0f, 0f, -1f);
        var cameraUp = new Vector3f(0f, 1f, 0f);
        viewMatrix.setLookAt(new Vector3f(position.x, position.y, 20f),
                cameraFront.add(position.x, position.y, 0f),
                cameraUp);

        return viewMatrix;
    }

}
