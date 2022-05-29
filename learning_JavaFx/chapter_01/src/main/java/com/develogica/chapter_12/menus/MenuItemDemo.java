package com.develogica.chapter_12.menus;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Objects;

public class MenuItemDemo extends Application {
    enum ShapeType { RECTANGLE, CIRCLE, ELLIPSE }
    Canvas canvas = new Canvas(200, 200);

    RadioMenuItem rectangleMenuItem = new RadioMenuItem("_Rectangle");
    RadioMenuItem circleMenuItem = new RadioMenuItem("_Circle");
    RadioMenuItem ellipseMenuItem = new RadioMenuItem("_Ellipse");

    CheckMenuItem strokeMenuItem = new CheckMenuItem("Draw _Stroke");

    Slider strokeWidthSlider = new Slider(1, 10, 1);
    CustomMenuItem strokeWidthMenuItem = new CustomMenuItem(strokeWidthSlider, false);
    ToolBar toolBar = new ToolBar();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        var fileMenu = getFileMenu();
        var viewMenu = getViewMenu();
        var optionsMenu = getOptionsMenu();

        var menuBar = new MenuBar(fileMenu, optionsMenu, viewMenu);

        this.draw();

        var contextMenuLabel = new Label("Right-click here to see context menu.");
        contextMenuLabel.setOnMouseClicked(this::showContextMenu);

        var root = new BorderPane();

        var rectangleToolBarButton = new Button("", new Rectangle(0, 0, 16, 16));
        var circleToolBarButton = new Button("", new Circle(0, 0, 8));
        var ellipseToolBarButton = new Button("", new Ellipse(8, 8, 8, 5));
        var exitToolBarButton = new Button("EXIT");

        rectangleToolBarButton.setTooltip(new Tooltip("Draws a rectangle."));
        circleToolBarButton.setTooltip(new Tooltip("Draws a circle."));
        ellipseToolBarButton.setTooltip(new Tooltip("Draws an ellipse."));
        exitToolBarButton.setTooltip(new Tooltip("Exits the application."));

        rectangleToolBarButton.setOnAction(event -> draw(ShapeType.RECTANGLE));
        circleToolBarButton.setOnAction(event -> draw(ShapeType.CIRCLE));
        ellipseToolBarButton.setOnAction(event -> draw(ShapeType.ELLIPSE));
        exitToolBarButton.setOnAction(event -> Platform.exit());

        toolBar.getItems().addAll(
                rectangleToolBarButton,
                circleToolBarButton,
                ellipseToolBarButton,
                exitToolBarButton
        );

        var menuArea = new VBox(menuBar, toolBar);

        root.setTop(menuArea);
        root.setCenter(canvas);
        root.setBottom(contextMenuLabel);
        root.setPadding(new Insets(5));

        var scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("MenuItem Demo");
        stage.show();

    }

    private Menu getViewMenu() {
        var toolbarMenuItem = new CheckMenuItem("Toolbar");
        toolbarMenuItem.setSelected(true);
        toolbarMenuItem.setOnAction(event -> toolBar.setVisible(toolbarMenuItem.isSelected()));
        var viewMenu = new Menu("View");
        viewMenu.getItems().add(toolbarMenuItem);
        return viewMenu;
    }

    private void showContextMenu(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == MouseButton.SECONDARY) {
            var rectangleCtxMenuItem = new MenuItem("Rectangle");
            rectangleCtxMenuItem.setOnAction(event -> draw(ShapeType.RECTANGLE));

            var circleCtxMenuItem = new MenuItem("Circle");
            circleCtxMenuItem.setOnAction(event -> draw(ShapeType.CIRCLE));

            var ellipseCtxMenuItem = new MenuItem("Ellipse");
            ellipseCtxMenuItem.setOnAction(event -> draw(ShapeType.ELLIPSE));

            var clearCtxMenuItem = new MenuItem("Clear");
            clearCtxMenuItem.setOnAction(event -> clear());


            var contextMenu = new ContextMenu(
                    rectangleCtxMenuItem,
                    circleCtxMenuItem,
                    ellipseCtxMenuItem,
                    new SeparatorMenuItem(),
                    clearCtxMenuItem);
            contextMenu.show(canvas, mouseEvent.getScreenX(), mouseEvent.getScreenY());
        }
    }

    private void draw(ShapeType shapeType) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, 200, 200);

        gc.setFill(Color.TAN);
        gc.setStroke(Color.RED);
        gc.setLineWidth(strokeWidthSlider.getValue());

        switch (Objects.requireNonNull(shapeType)) {
            case RECTANGLE -> {
                gc.fillRect(0, 0, 200, 200);
                if (strokeMenuItem.isSelected()) {
                    gc.strokeRect(0, 0, 200, 200);
                }
            }
            case CIRCLE -> {
                gc.fillOval(10, 10, 180, 180);
                if (strokeMenuItem.isSelected()) {
                    gc.strokeOval(10, 10, 180, 180);
                }
            }
            case ELLIPSE -> {
                gc.fillOval(10, 10, 100, 150);
                if (strokeMenuItem.isSelected()) {
                    gc.strokeOval(10, 10, 100, 150);
                }
            } default -> {
                clear();
            }
        }
    }
    private void draw() {
        draw(getSelectedShape());
    }

    private void clear() {
        canvas.getGraphicsContext2D().clearRect(0, 0, 200, 200);
        this.rectangleMenuItem.setSelected(false);
        this.circleMenuItem.setSelected(false);
        this.ellipseMenuItem.setSelected(false);
    }

    private ShapeType getSelectedShape() {
        if (rectangleMenuItem.isSelected()) {
            return ShapeType.RECTANGLE;
        } else if (circleMenuItem.isSelected()) {
            return ShapeType.CIRCLE;
        } else if (ellipseMenuItem.isSelected()) {
            return ShapeType.ELLIPSE;
        } else {
            return null;
        }
    }

    private Menu getFileMenu() {
        var fileMenu = new Menu("_File");

        rectangleMenuItem.setSelected(true);

        var keyCombR = new KeyCodeCombination(KeyCode.R, KeyCombination.ALT_DOWN);
        var keyCombC = new KeyCodeCombination(KeyCode.C, KeyCombination.ALT_DOWN);
        var keyCombE = new KeyCodeCombination(KeyCode.E, KeyCombination.ALT_DOWN);

        rectangleMenuItem.setAccelerator(keyCombR);
        circleMenuItem.setAccelerator(keyCombC);
        ellipseMenuItem.setAccelerator(keyCombE);

        rectangleMenuItem.setOnAction(event -> draw());
        circleMenuItem.setOnAction(event -> draw());
        ellipseMenuItem.setOnAction(event -> draw());

        var shapeGroup = new ToggleGroup();
        shapeGroup.getToggles().addAll(rectangleMenuItem, circleMenuItem, ellipseMenuItem);

        var clearMenuItem = new MenuItem("Cle_ar");
        clearMenuItem.setOnAction( event -> clear() );

        var exitMenuItem = new MenuItem("E_xit");
        exitMenuItem.setOnAction( event -> Platform.exit() );

        fileMenu.getItems().addAll(
                rectangleMenuItem,
                circleMenuItem,
                ellipseMenuItem,
                new SeparatorMenuItem(),
                clearMenuItem,
                new SeparatorMenuItem(),
                exitMenuItem
        );

        return fileMenu;
    }

    private Menu getOptionsMenu() {
        strokeMenuItem.setSelected(true);

        strokeMenuItem.setOnAction(event -> syncStroke());

        strokeWidthSlider.setShowTickLabels(true);
        strokeWidthSlider.setShowTickMarks(true);
        strokeWidthSlider.setMajorTickUnit(2);
        strokeWidthSlider.setSnapToPixel(true);
        strokeWidthSlider.valueProperty().addListener(this::strokeWidthChanged);

        var optionsMenu = new Menu("_Options");
        optionsMenu.getItems().addAll(strokeMenuItem, strokeWidthMenuItem);

        return optionsMenu;
    }

    private void strokeWidthChanged(ObservableValue<? extends Number> prop, Number oldValue, Number newValue) {
        draw();
    }

    private void syncStroke() {
        strokeWidthSlider.setDisable(!strokeMenuItem.isSelected());
        draw();
    }
}

