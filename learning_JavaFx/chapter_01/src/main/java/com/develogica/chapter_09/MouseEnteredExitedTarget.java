package com.develogica.chapter_09;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MouseEnteredExitedTarget extends Application {
    private final CheckBox consumeCheckBox = new CheckBox("Consume Events");
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        var circle = new Circle(50, 50, 50);
        circle.setFill(Color.SALMON);

        var root = new HBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.getChildren().addAll(circle, consumeCheckBox);

        EventHandler<MouseEvent> circleHandler = this::handleCircle;
        EventHandler<MouseEvent> circleTargetHandler = this::handleCircleTarget;
        EventHandler<MouseEvent> hBoxTargetHandler = this::handleHBoxTarget;

        root.addEventFilter(MouseEvent.MOUSE_ENTERED_TARGET, hBoxTargetHandler);
        root.addEventFilter(MouseEvent.MOUSE_EXITED_TARGET, hBoxTargetHandler);

        circle.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, circleTargetHandler);
        circle.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, circleTargetHandler);

        circle.addEventHandler(MouseEvent.MOUSE_ENTERED, circleHandler);
        circle.addEventHandler(MouseEvent.MOUSE_EXITED, circleHandler);



        var scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Mouse-Entered Mouse-Exited Target");
        stage.show();
    }

    private void handleHBoxTarget(MouseEvent mouseEvent) {
        print(mouseEvent, "HBox Target Filter");

        if (consumeCheckBox.isSelected()) {
            mouseEvent.consume();
            System.out.printf("HBox consumed the %s event%n", mouseEvent.getEventType());
        }
    }

    private void handleCircle(MouseEvent mouseEvent) {
        print(mouseEvent, "Circle Handler");
    }

    private void handleCircleTarget(MouseEvent event) {
        print(event, "Circle Target Handler");
    }

    private void print(MouseEvent event, String message) {
        System.out.printf("%s Type:%s Source:%s Target:%s%n",
                message,
                event.getEventType().getName(),
                event.getSource().getClass().getSimpleName(),
                event.getTarget().getClass().getSimpleName()
        );
    }
}
