package jade;

import java.awt.event.KeyEvent;

public class LevelEditorScene extends Scene {

    private boolean changingScene = false;
    private float timeToChangeScene = 2f;

    public LevelEditorScene() {
        System.out.println("Inside Level Editor scene");
    }

    @Override
    public void update(float dt) {

        System.out.printf("%f FPS%n", 1f/dt);

        if (!changingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE)) {
            changingScene = true;
        }

        if (changingScene && timeToChangeScene > 0) {
            timeToChangeScene -= dt;
            Window.get().r -= dt * 5f;
            Window.get().g -= dt * 5f;
            Window.get().b -= dt * 5f;
        } else if (changingScene) {
            Window.changeScene(1);
        }
    }
}


