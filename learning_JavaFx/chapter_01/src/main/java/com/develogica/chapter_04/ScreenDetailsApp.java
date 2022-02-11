package com.develogica.chapter_04;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ScreenDetailsApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        ObservableList<Screen> screens = Screen.getScreens();
        System.out.println("Screen count: " + screens.size());

        for (Screen screen : screens) {
            print(screen);
        }

        Platform.exit();
    }

    private void print(Screen screen) {
        System.out.println("DPI: " + screen.getDpi());
        System.out.print("Screen bounds: ");
        Rectangle2D bounds = screen.getBounds();
        print(bounds);

        System.out.print("Screen visual bounds: ");
        Rectangle2D visualBounds = screen.getVisualBounds();
        print(visualBounds);

        System.out.println("-".repeat(30));
    }

    private void print(Rectangle2D rectangle) {
        System.out.printf("minX:%.2f minY:%.2f width:%.2f height:%.2f%n",
                rectangle.getMinX(), rectangle.getMinY(),
                rectangle.getWidth(), rectangle.getHeight());
    }
}
