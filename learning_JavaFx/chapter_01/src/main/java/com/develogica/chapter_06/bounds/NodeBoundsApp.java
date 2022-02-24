package com.develogica.chapter_06.bounds;

import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.*;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.converter.DoubleStringConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class NodeBoundsApp extends Application {
    final static int AXIS_LENGTH = 150;
    final static int ARROW_LENGTH = 5;

    final static int RECTANGLE_WIDTH = 50;
    final static int RECTANGLE_HEIGHT = 20;
    final static double RECTANGLE_OPACITY = 0.5;

    final static Color RECTANGLE_STROKE_COLOR = Color.PURPLE;
    final static String RECTANGLE_STROKE_COLOR_STR = "PURPLE";
    final static double RECTANGLE_STROKE_WIDTH = 0.0;
    final static Color RECTANGLE_FILL_COLOR = Color.DARKGRAY;
    final static String RECTANGLE_FILL_COLOR_STR = "DARKGRAY";

    final Color LOCAL_BOUNDS_RECT_FILL_COLOR = Color.WHITE;
    final Color PARENT_BOUNDS_RECT_FILL_COLOR = Color.WHITE;
    final Color LAYOUT_BOUNDS_RECT_FILL_COLOR = Color.WHITE;

    final Color LOCAL_BOUNDS_RECT_STROKE_COLOR = Color.BLUE;
    final Color PARENT_BOUNDS_RECT_STROKE_COLOR = Color.RED;
    final Color LAYOUT_BOUNDS_RECT_STROKE_COLOR = Color.BLACK;

    final double BOUNDS_STROKE_WIDTH = 2.0;
    final double BOUNDS_PATH_CIRCLE_RADIUS = 4.0;

    // Animating the bounds bounding boxes
    final Circle LOCAL_BOUNDS_PATH_CIRCLE = new Circle(0, 0, BOUNDS_PATH_CIRCLE_RADIUS);
    final Circle PARENT_BOUNDS_PATH_CIRCLE = new Circle(0, 0, BOUNDS_PATH_CIRCLE_RADIUS);
    final Circle LAYOUT_BOUNDS_PATH_CIRCLE = new Circle(0, 0, BOUNDS_PATH_CIRCLE_RADIUS);

    final PathTransition LOCAL_BOUNDS_PATH_TRANSITION = new PathTransition();
    final PathTransition PARENT_BOUNDS_PATH_TRANSITION = new PathTransition();
    final PathTransition LAYOUT_BOUNDS_PATH_TRANSITION = new PathTransition();

    Slider xSlider = new Slider(-100, 100, 0);
    Slider ySlider = new Slider(-100, 100, 0);
    Slider widthSlider = new Slider(10, 200, RECTANGLE_WIDTH);
    Slider heightSlider = new Slider(10, 200, RECTANGLE_HEIGHT);
    Slider opacitySlider = new Slider(0, 1.0, RECTANGLE_OPACITY);
    Slider strokeSlider = new Slider(0.0, 10.0, RECTANGLE_STROKE_WIDTH);

    ObservableList<String> colorList = FXCollections.observableArrayList(
            "DARKBLUE", "DARKGRAY", "DARKRED", "BLACK",
            "BLUE", "RED", "GREEN", "GRAY", "PURPLE", "YELLOW"
    );
    ChoiceBox<String> rectFillColorChoiceBox = new ChoiceBox<>(colorList);
    ChoiceBox<String> rectStrokeColorChoiceBox = new ChoiceBox<>(colorList);
    Slider translateXSlider = new Slider(-200, 200, 0);
    Slider translateYSlider = new Slider(-200, 200, 0);

    Slider rotateSlider = new Slider(0, 360, 0);
    Slider scaleXSlider = new Slider(0.20, 2.0, 1.0);
    Slider scaleYSlider = new Slider(0.20, 2.0, 1.0);
    Slider shearXSlider = new Slider(-1.0, 1.0, 0);
    Slider shearYSlider = new Slider(-1.0, 1.0, 0);

    Label xLabel = new Label("");
    Label yLabel = new Label("");
    Label widthLabel = new Label("");
    Label heightLabel = new Label("");
    Label opacityLabel = new Label("");
    Label strokeLabel = new Label("");
    Label translateXLabel = new Label("");
    Label translateYLabel = new Label("");
    Label rotateLabel = new Label("");
    Label scaleXLabel = new Label("");
    Label scaleYLabel = new Label("");
    Label shearXLabel = new Label("");
    Label shearYLabel = new Label("");

    Rectangle mainRect = new Rectangle(0, 0, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);

    Rectangle localBoundsRect = new Rectangle();
    Rectangle parentBoundsRect = new Rectangle();
    Rectangle layoutBoundsRect = new Rectangle();

    Line localXAxis = new Line(0, 0, AXIS_LENGTH, 0);
    Line localYAxis = new Line(0, 0, 0, AXIS_LENGTH);

    Polygon localXArrow = new Polygon(AXIS_LENGTH, -ARROW_LENGTH, AXIS_LENGTH, ARROW_LENGTH,
            AXIS_LENGTH + ARROW_LENGTH, 0, AXIS_LENGTH, -ARROW_LENGTH);
    Text localXAxisLabel = new Text("x-Axis");

    Polygon localYArrow = new Polygon(0, AXIS_LENGTH + ARROW_LENGTH, -ARROW_LENGTH,
            AXIS_LENGTH, ARROW_LENGTH, AXIS_LENGTH, 0, AXIS_LENGTH + ARROW_LENGTH);
    Text localYAxisLabel = new Text("y-Axis");

    Group localXAxisGroup = new Group(localXAxis, localXArrow, localXAxisLabel);
    Group localYAxisGroup = new Group(localYAxis, localYArrow, localYAxisLabel);

    Line parentXAxis = new Line(0, 0, AXIS_LENGTH, 0);
    Line parentYAxis = new Line(0, 0, 0, AXIS_LENGTH);

    Polygon parentXArrow = new Polygon(AXIS_LENGTH, -ARROW_LENGTH, AXIS_LENGTH, ARROW_LENGTH,
            AXIS_LENGTH + ARROW_LENGTH, 0, AXIS_LENGTH, -ARROW_LENGTH);
    Text parentXAxisLabel = new Text("x-Axis");

    Polygon parentYArrow = new Polygon(0, AXIS_LENGTH + ARROW_LENGTH, -ARROW_LENGTH,
            AXIS_LENGTH, ARROW_LENGTH, AXIS_LENGTH, 0, AXIS_LENGTH + ARROW_LENGTH);
    Text parentYAxisLabel = new Text("y-Axis");

    Group parentXAxisGroup = new Group(parentXAxis, parentXArrow, parentXAxisLabel);
    Group parentYAxisGroup = new Group(parentYAxis, parentYArrow, parentYAxisLabel);

    CheckBox layoutCbx = new CheckBox("Show");
    CheckBox localCbx = new CheckBox("Show");
    CheckBox parentCbx = new CheckBox("Show");
    CheckBox layoutAnimateCbx = new CheckBox("Animate");
    CheckBox localAnimateCbx = new CheckBox("Animate");
    CheckBox parentAnimateCbx = new CheckBox("Animate");

    ToggleGroup effectGroup = new ToggleGroup();
    RadioButton noneEffect = new RadioButton("None");
    RadioButton dropShadowEffect = new RadioButton("DropShadow");
    RadioButton reflectionEffect = new RadioButton("Reflection");
    RadioButton glowEffect = new RadioButton("Glow");

    BoundsRecord layoutBoundsData = new BoundsRecord("layoutBounds");
    BoundsRecord localBoundsData = new BoundsRecord("boundsInLocal");
    BoundsRecord parentBoundsData = new BoundsRecord("boundsInParent");

    ObservableList<BoundsRecord> boundsRecords = FXCollections.observableArrayList(
            layoutBoundsData, localBoundsData, parentBoundsData);
    private final TableView<BoundsRecord> boundsTableView = new TableView<>(boundsRecords);
    private Node boundsLayoutNode;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        int axisLabelPos = AXIS_LENGTH + ARROW_LENGTH + 4;
        localXAxisLabel.setLayoutX(axisLabelPos);
        localXAxisLabel.setTextOrigin(VPos.CENTER);

        localYAxisLabel.setLayoutX(axisLabelPos);
        localYAxisLabel.setTextOrigin(VPos.CENTER);

        parentXAxisLabel.setLayoutX(axisLabelPos);
        parentXAxisLabel.setTextOrigin(VPos.CENTER);

        parentYAxisLabel.setLayoutY(axisLabelPos);
        parentYAxisLabel.setTextOrigin(VPos.CENTER);

        String textFont = "-fx-font-size: 9;";
        localXAxisLabel.setStyle(textFont);
        localYAxisLabel.setStyle(textFont);
        parentXAxisLabel.setStyle(textFont);
        parentYAxisLabel.setStyle(textFont);

        mainRect.setFill(RECTANGLE_FILL_COLOR);
        rectFillColorChoiceBox.setValue(RECTANGLE_FILL_COLOR_STR);
        rectStrokeColorChoiceBox.setValue(RECTANGLE_STROKE_COLOR_STR);

        initializeBoundsDetails();
        initializeBoundsPathTransition();

        bindLabelsTSliders();

        initializeBoundsTableView();

    }

    private void initializeBoundsPathTransition() {
        LOCAL_BOUNDS_PATH_TRANSITION.setDuration(Duration.seconds(2));
        LOCAL_BOUNDS_PATH_TRANSITION.setNode(LOCAL_BOUNDS_PATH_CIRCLE);
        LOCAL_BOUNDS_PATH_TRANSITION.setOrientation(PathTransition.OrientationType.NONE);
        LOCAL_BOUNDS_PATH_TRANSITION.setCycleCount(Timeline.INDEFINITE);

        PARENT_BOUNDS_PATH_TRANSITION.setDuration(Duration.seconds(2));
        PARENT_BOUNDS_PATH_TRANSITION.setNode(PARENT_BOUNDS_PATH_CIRCLE);
        PARENT_BOUNDS_PATH_TRANSITION.setOrientation(PathTransition.OrientationType.NONE);
        PARENT_BOUNDS_PATH_TRANSITION.setCycleCount(Timeline.INDEFINITE);

        LAYOUT_BOUNDS_PATH_TRANSITION.setDuration(Duration.seconds(2));
        LAYOUT_BOUNDS_PATH_TRANSITION.setNode(LAYOUT_BOUNDS_PATH_CIRCLE);
        LAYOUT_BOUNDS_PATH_TRANSITION.setOrientation(PathTransition.OrientationType.NONE);
        LAYOUT_BOUNDS_PATH_TRANSITION.setCycleCount(Timeline.INDEFINITE);


    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();

        Pane parentContainer = new Pane();
        parentContainer.setPrefSize(500, 500);
        parentContainer.setPickOnBounds(false);

        Group parent = new Group();
        boundsLayoutNode = parent;

        parent.setLayoutX(200);
        parent.setLayoutY(200);
        parent.setStyle("-fx-background-color:white;");
        parent.getChildren().addAll(
                new Group(localXAxisGroup, localYAxisGroup),
                new Group(parentXAxisGroup, parentYAxisGroup),
                new Group(parentBoundsRect, PARENT_BOUNDS_PATH_CIRCLE),
                new Group(localBoundsRect, LOCAL_BOUNDS_PATH_CIRCLE),
                new Group(layoutBoundsRect, LAYOUT_BOUNDS_PATH_CIRCLE),
                new Group(mainRect)
        );

        parentContainer.getChildren().addAll(parent);

        VBox transformsControls = getTransformationControls();
        VBox resultsControls = getResultsControls();

        BorderPane nestedPane = new BorderPane();
        nestedPane.setCenter(parentContainer);
        nestedPane.setBottom(resultsControls);

        root.setCenter(nestedPane);
        root.setRight(transformsControls);

        attachEventHandlers();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Bounds of a Node");
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        stage.setX(visualBounds.getMinX());
        stage.setY(visualBounds.getMaxY());
        stage.setWidth(visualBounds.getWidth());
        stage.setHeight(visualBounds.getHeight());
        stage.show();

        relayout();
    }

    private void relayout() {
        mainRect.getTransforms().clear();

        mainRect.setWidth(widthSlider.getValue());
        mainRect.setHeight(heightSlider.getValue());
        mainRect.setX(xSlider.getValue());
        mainRect.setY(ySlider.getValue());

        mainRect.getTransforms().addAll(getTransforms(true, true));

        mainRect.setEffect(this.getEffect());

        mainRect.setStrokeWidth(strokeSlider.getValue());
        mainRect.setStroke(RECTANGLE_STROKE_COLOR);
        mainRect.setStroke(Color.web(rectStrokeColorChoiceBox.getValue()));

        drawAllAxes();
        displayResults();
        showBounds();
        restartBoundsTransitions();
    }

    private void displayResults() {
        Bounds layoutBounds = mainRect.getLayoutBounds();
        Bounds localBounds = mainRect.getBoundsInLocal();
        Bounds parentBounds = mainRect.getBoundsInParent();

        layoutBoundsData.update(layoutBounds);
        localBoundsData.update(localBounds);
        parentBoundsData.update(parentBounds);

    }

    private void drawAllAxes() {
        double x = 100;
        double y = 100;

        Point2D point = mainRect.localToParent(x, y);

        if (point.getY() == y) {
            parentXArrow.setVisible(false);
            parentXAxisLabel.setVisible(false);

            localXAxisLabel.setText("x-axis (Patent/Untransformed Local)");
            drawLocalAxis();
        } else {
            this.parentXArrow.setVisible(true);
            this.parentXAxisLabel.setVisible(true);

            localXAxisLabel.setText("x-axis (Transformed Local)");
            parentXAxisLabel.setText("x-axis (Parent/Untransformed Local)");

            drawLocalAxis();
            drawParentAxis();
        }

 if (point.getX() == x) {
            parentYArrow.setVisible(false);
            parentYAxisLabel.setVisible(false);

            localYAxisLabel.setText("y-axis (Patent/Untransformed Local)");
            drawLocalAxis();
        } else {
            this.parentYArrow.setVisible(true);
            this.parentYAxisLabel.setVisible(true);

            localYAxisLabel.setText("y-axis (Transformed Local)");
            parentYAxisLabel.setText("y-axis (Parent/Untransformed Local)");

            drawLocalAxis();
            drawParentAxis();
        }

    }

    private void drawParentAxis() {
        parentYAxisLabel.setLayoutX(-1.0 * parentYAxisLabel.layoutBoundsProperty()
                .get().getWidth() / 2.0);
    }

    private void drawLocalAxis() {
        localYAxisLabel.setLayoutX(-1.0 * parentYAxisLabel.layoutBoundsProperty()
                .get().getWidth() / 2.0);

        localXAxisGroup.getTransforms().clear();
        localYAxisGroup.getTransforms().clear();

        List<Transform> transforms = getTransforms(true, true);
        localYAxisGroup.getTransforms().addAll(transforms);
        localYAxisGroup.getTransforms().addAll(transforms);
    }

    private Effect getEffect() {
        Effect effect = null;
        if (dropShadowEffect.isSelected()) {
            effect = new DropShadow();
        } else if (reflectionEffect.isSelected()) {
            effect = new Reflection();
        } else if (glowEffect.isSelected()) {
            effect = new Glow();
        }

        return effect;
    }

    private void showBounds() {
        if (layoutCbx.isSelected()) {
            Bounds bounds = mainRect.getLayoutBounds();
            layoutBoundsRect.setX(bounds.getMinX());
            layoutBoundsRect.setY(bounds.getMinY());
            layoutBoundsRect.setWidth(bounds.getWidth());
            layoutBoundsRect.setHeight(bounds.getHeight());

            layoutBoundsRect.setVisible(true);
        } else {
            layoutBoundsRect.setVisible(false);
        }


        if (parentCbx.isSelected()) {
            Bounds bounds = mainRect.getBoundsInParent();
            parentBoundsRect.setX(bounds.getMinX());
            parentBoundsRect.setY(bounds.getMinY());
            parentBoundsRect.setWidth(bounds.getWidth());
            parentBoundsRect.setHeight(bounds.getHeight());

            parentBoundsRect.setVisible(true);
        } else {
            parentBoundsRect.setVisible(false);
        }

        if (localCbx.isSelected()) {
            Bounds bounds = mainRect.getBoundsInLocal();
            localBoundsRect.setX(bounds.getMinX());
            localBoundsRect.setY(bounds.getMinY());
            localBoundsRect.setWidth(bounds.getWidth());
            localBoundsRect.setHeight(bounds.getHeight());

            localBoundsRect.setVisible(true);
        } else {
            localBoundsRect.setVisible(false);
        }
    }

    private List<Transform> getTransforms(boolean includeScale, boolean includeShear) {
        double tx = translateXSlider.getValue();
        double ty = translateYSlider.getValue();

        double scaleX = shearXSlider.getValue();
        double scaleY = shearYSlider.getValue();
        double shearX = shearXSlider.getValue();
        double shearY = shearYSlider.getValue();
        double rotation = rotateSlider.getValue();

        List<Transform> transforms = new ArrayList<>();

        transforms.add(new Translate(tx, ty));
        transforms.add(new Rotate(rotation));

        if (includeScale) {
            transforms.add(new Scale(scaleX, scaleY));
        }
        if (includeShear) {
            transforms.add(new Shear(shearX, shearY));
        }

        return transforms;
    }

    private void restartBoundsTransitions() {
        LAYOUT_BOUNDS_PATH_TRANSITION.stop();
        PARENT_BOUNDS_PATH_TRANSITION.stop();
        LOCAL_BOUNDS_PATH_TRANSITION.stop();
        this.animate();
    }

    private void animate() {
        if (layoutAnimateCbx.isSelected()) {
            LAYOUT_BOUNDS_PATH_CIRCLE.setVisible(true);
            playLayoutBoundsPathTransition();
        } else {
            LAYOUT_BOUNDS_PATH_CIRCLE.setVisible(false);
            LAYOUT_BOUNDS_PATH_TRANSITION.stop();
        }

        if (localAnimateCbx.isSelected()) {
            LOCAL_BOUNDS_PATH_CIRCLE.setVisible(true);
            playLocalBoundsPathTransition();
        } else {
            LOCAL_BOUNDS_PATH_CIRCLE.setVisible(false);
            LOCAL_BOUNDS_PATH_TRANSITION.stop();
        }

        if (parentAnimateCbx.isSelected()) {
            PARENT_BOUNDS_PATH_CIRCLE.setVisible(true);
            playParentBoundsPathTransition();
        } else {
            PARENT_BOUNDS_PATH_CIRCLE.setVisible(false);
            PARENT_BOUNDS_PATH_TRANSITION.stop();
        }


    }

    private void playLayoutBoundsPathTransition() {
        Bounds bounds = mainRect.getLayoutBounds();
        Path path = new Path();
        path.getElements().add(new MoveTo(bounds.getMinX(), bounds.getMinY()));
        path.getElements().add(new LineTo(bounds.getMaxX(), bounds.getMinY()));
        path.getElements().add(new LineTo(bounds.getMaxX(), bounds.getMaxY()));
        path.getElements().add(new LineTo(bounds.getMinX(), bounds.getMaxY()));
        path.getElements().add(new LineTo(bounds.getMinX(), bounds.getMinY()));

        LAYOUT_BOUNDS_PATH_TRANSITION.setPath(path);
        LAYOUT_BOUNDS_PATH_TRANSITION.play();
    }

    private void playLocalBoundsPathTransition() {
        Bounds bounds = mainRect.getBoundsInLocal();
        Path path = new Path();
        path.getElements().add(new MoveTo(bounds.getMinX(), bounds.getMinY()));
        path.getElements().add(new LineTo(bounds.getMaxX(), bounds.getMinY()));
        path.getElements().add(new LineTo(bounds.getMaxX(), bounds.getMaxY()));
        path.getElements().add(new LineTo(bounds.getMinX(), bounds.getMaxY()));
        path.getElements().add(new LineTo(bounds.getMinX(), bounds.getMinY()));

        LOCAL_BOUNDS_PATH_TRANSITION.setPath(path);
        LOCAL_BOUNDS_PATH_TRANSITION.play();
    }

    private void playParentBoundsPathTransition() {
        Bounds bounds = mainRect.getBoundsInParent();
        Path path = new Path();
        path.getElements().add(new MoveTo(bounds.getMinX(), bounds.getMinY()));
        path.getElements().add(new LineTo(bounds.getMaxX(), bounds.getMinY()));
        path.getElements().add(new LineTo(bounds.getMaxX(), bounds.getMaxY()));
        path.getElements().add(new LineTo(bounds.getMinX(), bounds.getMaxY()));
        path.getElements().add(new LineTo(bounds.getMinX(), bounds.getMinY()));

        PARENT_BOUNDS_PATH_TRANSITION.setPath(path);
        PARENT_BOUNDS_PATH_TRANSITION.play();
    }

    private void attachEventHandlers() {
        ChangeListener<Number> listener = (observableValue, oldValue, newValue) -> relayout();
        ChangeListener<String> listenerStr = (observableValue, oldValue, newValue) -> relayout();

        xSlider.valueProperty().addListener(listener);
        ySlider.valueProperty().addListener(listener);
        widthSlider.valueProperty().addListener(listener);
        heightSlider.valueProperty().addListener(listener);

        strokeSlider.valueProperty().addListener(listener);
        rectStrokeColorChoiceBox.valueProperty().addListener(listenerStr);

        opacitySlider.valueProperty().addListener(listener);
        rectFillColorChoiceBox.valueProperty().addListener(listenerStr);

        translateXSlider.valueProperty().addListener(listener);
        translateYSlider.valueProperty().addListener(listener);
        rotateSlider.valueProperty().addListener(listener);
        scaleXSlider.valueProperty().addListener(listener);
        scaleYSlider.valueProperty().addListener(listener);
        shearXSlider.valueProperty().addListener(listener);
        shearYSlider.valueProperty().addListener(listener);
    }

    private VBox getTransformationControls() {
        xSlider.setShowTickMarks(true);
        xSlider.setShowTickLabels(true);
        xSlider.snapToTicksProperty().set(true);

        ySlider.setShowTickMarks(true);
        ySlider.setShowTickLabels(true);
        ySlider.snapToTicksProperty().set(true);

        widthSlider.setShowTickMarks(true);
        widthSlider.setShowTickLabels(true);
        widthSlider.snapToTicksProperty().set(true);

        heightSlider.setShowTickMarks(true);
        heightSlider.setShowTickLabels(true);
        heightSlider.snapToTicksProperty().set(true);

        opacitySlider.setShowTickMarks(true);
        opacitySlider.setShowTickLabels(true);
        opacitySlider.snapToTicksProperty().set(true);
        opacitySlider.setMinorTickCount(5);
        opacitySlider.setMajorTickUnit(1.0d);

        strokeSlider.setShowTickMarks(true);
        strokeSlider.setShowTickLabels(true);
        strokeSlider.snapToTicksProperty().set(true);
        strokeSlider.setMinorTickCount(5);
        strokeSlider.setMajorTickUnit(0.20d);

        translateXSlider.setShowTickMarks(true);
        translateXSlider.setShowTickLabels(true);
        translateXSlider.snapToTicksProperty().set(true);

        translateYSlider.setShowTickMarks(true);
        translateYSlider.setShowTickLabels(true);
        translateYSlider.snapToTicksProperty().set(true);

        rotateSlider.setShowTickMarks(true);
        rotateSlider.setShowTickLabels(true);
        rotateSlider.snapToTicksProperty().set(true);
        rotateSlider.setMinorTickCount(5);
        rotateSlider.setMajorTickUnit(30.0);

        scaleXSlider.setShowTickMarks(true);
        scaleXSlider.setShowTickLabels(true);
        scaleXSlider.setMajorTickUnit(0.20d);
        scaleXSlider.setLabelFormatter(new DoubleStringConverter());
        scaleXSlider.snapToTicksProperty().set(true);

        scaleYSlider.setShowTickMarks(true);
        scaleYSlider.setShowTickLabels(true);
        scaleYSlider.setMajorTickUnit(0.20d);
        scaleYSlider.setLabelFormatter(new DoubleStringConverter());
        scaleYSlider.snapToTicksProperty().set(true);

        shearXSlider.setShowTickMarks(true);
        shearXSlider.setShowTickLabels(true);
        shearXSlider.setMajorTickUnit(0.20d);
        shearXSlider.setLabelFormatter(new DoubleStringConverter());
        shearXSlider.snapToTicksProperty().set(true);

        shearYSlider.setShowTickMarks(true);
        shearYSlider.setShowTickLabels(true);
        shearYSlider.setMajorTickUnit(0.20d);
        shearYSlider.setLabelFormatter(new DoubleStringConverter());
        shearYSlider.snapToTicksProperty().set(true);

        HBox xyBox = new HBox();
        xyBox.setSpacing(5);
        xyBox.getChildren().addAll(
                VBoxUtil.fromChildren(xLabel, xSlider),
                VBoxUtil.fromChildren(yLabel, ySlider)
        );

        HBox whBox = new HBox();
        whBox.setSpacing(5);
        whBox.getChildren().addAll(
                VBoxUtil.fromChildren(widthLabel, widthSlider),
                VBoxUtil.fromChildren(heightLabel, heightSlider)
        );

        HBox colorBox = new HBox();
        colorBox.setSpacing(5);
        colorBox.getChildren().addAll(
                VBoxUtil.fromChildren(strokeLabel, strokeSlider),
                VBoxUtil.fromChildren(new Label("Stroke Color"), rectStrokeColorChoiceBox)
        );

        HBox opacityBox = new HBox();
        opacityBox.setSpacing(5);
        opacityBox.getChildren().addAll(
                VBoxUtil.fromChildren(opacityLabel, opacitySlider),
                VBoxUtil.fromChildren(new Label("Fill Color"), rectFillColorChoiceBox)
        );

        HBox translateBox = new HBox();
        translateBox.setSpacing(5);
        translateBox.getChildren().addAll(
                VBoxUtil.fromChildren(translateXLabel, translateXSlider),
                VBoxUtil.fromChildren(translateYLabel, translateYSlider)
        );

        HBox rotateBox = new HBox();
        rotateBox.setSpacing(5);
        rotateBox.getChildren().addAll(
                VBoxUtil.fromChildren(rotateLabel, rotateSlider)
        );

        HBox scaleBox = new HBox();
        scaleBox.setSpacing(5);
        scaleBox.getChildren().addAll(
                VBoxUtil.fromChildren(scaleXLabel, scaleXSlider),
                VBoxUtil.fromChildren(scaleYLabel, scaleYSlider)
        );

        HBox shearBox = new HBox();
        shearBox.setSpacing(5);
        shearBox.getChildren().addAll(
                VBoxUtil.fromChildren(shearXLabel, shearXSlider),
                VBoxUtil.fromChildren(shearYLabel, shearYSlider)
        );

        HBox rectangleBox = new HBox();
        rectangleBox.getChildren().addAll(xyBox, whBox, colorBox, opacityBox);
        TitledPane rectangleProps = new TitledPane("Rectangle", rectangleBox);

        VBox transformBox = new VBox();
        transformBox.getChildren().addAll(translateBox, rotateBox, scaleBox, shearBox);
        TitledPane transformProps = new TitledPane("Transformations", transformBox);

        TitledPane showBoundsControls = getShowBoundsControls();
        TitledPane effectPane = getEffectTitledPane();

        Button resetAllButton = new Button("Reset All");
        resetAllButton.setOnAction(event -> resetAll());

        Button saveButton = new Button("Save Layout as Image");
        saveButton.setOnAction(event -> saveLayoutAsImage());

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> Platform.exit());

        HBox buttonBox = new HBox();
        buttonBox.setSpacing(10);
        buttonBox.getChildren().addAll(resetAllButton, saveButton, exitButton);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                buttonBox, showBoundsControls, rectangleProps, effectPane, transformProps);

        return vBox;
    }

    private void resetAll() {
        xSlider.setValue(0.0);
        ySlider.setValue(0.0);
        widthSlider.setValue(RECTANGLE_WIDTH);
        heightSlider.setValue(RECTANGLE_HEIGHT);
        translateXSlider.setValue(0);
        translateYSlider.setValue(0);
        rotateSlider.setValue(0);
        scaleXSlider.setValue(1.0);
        scaleYSlider.setValue(1.0);
        shearXSlider.setValue(0);
        shearYSlider.setValue(0);

        strokeSlider.setValue(RECTANGLE_STROKE_WIDTH);
        rectStrokeColorChoiceBox.setValue(RECTANGLE_STROKE_COLOR_STR);

        opacitySlider.setValue(RECTANGLE_OPACITY);
        rectFillColorChoiceBox.setValue(RECTANGLE_FILL_COLOR_STR);

        layoutAnimateCbx.setSelected(false);
        parentAnimateCbx.setSelected(false);
        localAnimateCbx.setSelected(false);

        layoutCbx.setSelected(false);
        parentCbx.setSelected(false);
        localCbx.setSelected(false);

        noneEffect.setSelected(true);
    }

    private TitledPane getEffectTitledPane() {
        noneEffect.setToggleGroup(effectGroup);
        noneEffect.setSelected(true);

        dropShadowEffect.setToggleGroup(effectGroup);
        reflectionEffect.setToggleGroup(effectGroup);
        glowEffect.setToggleGroup(effectGroup);

        var hBox = new HBox();
        hBox.setPadding(new Insets(5));
        hBox.getChildren().addAll(noneEffect, dropShadowEffect, reflectionEffect, glowEffect);

        return new TitledPane("Effect", hBox);
    }

    private TitledPane getShowBoundsControls() {
        ChangeListener<Boolean> listener1 = (observable, oldValue, newValue) -> relayout();
        ChangeListener<Boolean> listener2 = (observable, oldValue, newValue) -> animate();

        layoutCbx.selectedProperty().addListener(listener1);
        parentCbx.selectedProperty().addListener(listener1);
        localCbx.selectedProperty().addListener(listener1);

        effectGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> relayout());

        layoutAnimateCbx.selectedProperty().addListener(listener2);
        parentAnimateCbx.selectedProperty().addListener(listener2);
        localAnimateCbx.selectedProperty().addListener(listener2);

        double w = 20.0;
        double h = 10.0;

        Rectangle recLayout = new Rectangle(w, h);
        recLayout.setFill(LAYOUT_BOUNDS_RECT_FILL_COLOR);
        recLayout.setStrokeWidth(BOUNDS_STROKE_WIDTH);
        recLayout.setStroke(LAYOUT_BOUNDS_RECT_STROKE_COLOR);

        Rectangle recParent = new Rectangle(w, h);
        recParent.setFill(PARENT_BOUNDS_RECT_FILL_COLOR);
        recParent.setStrokeWidth(BOUNDS_STROKE_WIDTH);
        recParent.setStroke(PARENT_BOUNDS_RECT_FILL_COLOR);

        Rectangle recLocal = new Rectangle(w, h);
        recLocal.setFill(LOCAL_BOUNDS_RECT_FILL_COLOR);
        recLocal.setStrokeWidth(BOUNDS_STROKE_WIDTH);
        recLocal.setStroke(LOCAL_BOUNDS_RECT_STROKE_COLOR);

        GridPane gridPane = new GridPane();
        gridPane.addRow(1, recLayout, new Text("Layout Bounds:"), layoutCbx, layoutAnimateCbx);
        gridPane.addRow(2, recParent, new Text("Parent Bounds:"), parentCbx, parentAnimateCbx);
        gridPane.addRow(3, recLocal, new Text("Local Bounds:"), localCbx, localAnimateCbx);

        TitledPane titledPane = new TitledPane("Show Bounds", gridPane);

        return titledPane;
    }

    private void saveLayoutAsImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters()
                .addAll(new FileChooser.ExtensionFilter(
                        "Portable Network Graphics(*.jpg)", Collections.singletonList("*.png")));

        File output = fileChooser.showSaveDialog(boundsLayoutNode.getScene().getWindow());
        if (output == null) {
            return;
        } else {
            String fn = output.getName();
            if (!fn.toLowerCase().endsWith(".png")) {
                output = new File(output.getParent(), fn + ".png");
            }
        }

        try {
            WritableImage wImage = boundsLayoutNode.snapshot(null, null);

            BufferedImage bufferedImg = SwingFXUtils.fromFXImage(wImage, null);
            ImageIO.write(bufferedImg, "png", output);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * getResultsControls
     */
    private VBox getResultsControls() {
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.getChildren().addAll(boundsTableView);
        return vBox;
    }

    private void initializeBoundsDetails() {
        localBoundsRect.setStroke(LOCAL_BOUNDS_RECT_STROKE_COLOR);
        localBoundsRect.setFill(LOCAL_BOUNDS_RECT_FILL_COLOR);
        localBoundsRect.setStrokeWidth(BOUNDS_STROKE_WIDTH);
        localBoundsRect.setOpacity(0.5);

        parentBoundsRect.setStroke(PARENT_BOUNDS_RECT_STROKE_COLOR);
        parentBoundsRect.setFill(PARENT_BOUNDS_RECT_FILL_COLOR);
        parentBoundsRect.setStrokeWidth(BOUNDS_STROKE_WIDTH);
        parentBoundsRect.setOpacity(0.5);

        layoutBoundsRect.setStroke(LAYOUT_BOUNDS_RECT_STROKE_COLOR);
        layoutBoundsRect.setFill(LAYOUT_BOUNDS_RECT_FILL_COLOR);
        layoutBoundsRect.setStrokeWidth(BOUNDS_STROKE_WIDTH);
        layoutBoundsRect.setOpacity(0.5);

        LOCAL_BOUNDS_PATH_CIRCLE.setFill(LOCAL_BOUNDS_RECT_STROKE_COLOR);
        PARENT_BOUNDS_PATH_CIRCLE.setFill(PARENT_BOUNDS_RECT_STROKE_COLOR);
        LAYOUT_BOUNDS_PATH_CIRCLE.setFill(LAYOUT_BOUNDS_RECT_STROKE_COLOR);

        LOCAL_BOUNDS_PATH_CIRCLE.setVisible(false);
        PARENT_BOUNDS_PATH_CIRCLE.setVisible(false);
        LAYOUT_BOUNDS_PATH_CIRCLE.setVisible(false);
    }


    private void bindLabelsTSliders() {
        xLabel.textProperty().bind(
                new SimpleStringProperty("x:")
                        .concat(xSlider.valueProperty().asString("%.2f"
                        ))
        );

        yLabel.textProperty().bind(
                new SimpleStringProperty("y:")
                        .concat(ySlider.valueProperty().asString("%.2f"
                        ))
        );

        widthLabel.textProperty().bind(
                new SimpleStringProperty("width:")
                        .concat(widthSlider.valueProperty().asString("%.2f"))
        );
        heightLabel.textProperty().bind(
                new SimpleStringProperty("height:")
                        .concat(heightSlider.valueProperty().asString("%.2f"))
        );
        opacityLabel.textProperty().bind
                (new SimpleStringProperty("opacity:")
                        .concat(opacitySlider.valueProperty().asString("%.2f"))
                );
        strokeLabel.textProperty().bind(
                new SimpleStringProperty("stroke:")
                        .concat(strokeSlider.valueProperty().asString("%.2f"))
        );

        translateXLabel.textProperty().bind(
                new SimpleStringProperty("TranslateX:")
                        .concat(translateXSlider.valueProperty().asString("%.2f"))
        );
        translateYLabel.textProperty().bind(
                new SimpleStringProperty("TranslateY:")
                        .concat(translateYSlider.valueProperty().asString("%.2f"))
        );

        rotateLabel.textProperty().bind(
                new SimpleStringProperty("Rotate:")
                        .concat(rotateSlider.valueProperty()
                                .asString("%.2f")).concat("deg")
        );

        scaleXLabel.textProperty().bind(
                new SimpleStringProperty("scaleX:")
                        .concat(scaleXSlider.valueProperty()
                                .asString("%.2f"))
        );
        scaleYLabel.textProperty().bind(
                new SimpleStringProperty("scaleY:")
                        .concat(scaleYSlider.valueProperty()
                                .asString("%.2f"))
        );

        shearXLabel.textProperty().bind(
                new SimpleStringProperty("shearX:")
                        .concat(shearXSlider.valueProperty()
                                .asString("%.2f"))
        );
        shearYLabel.textProperty().bind(
                new SimpleStringProperty("shearY:")
                        .concat(shearYSlider.valueProperty()
                                .asString("%.2f"))
        );

    }

    private void initializeBoundsTableView() {
        boundsTableView.setPrefSize(200, 100);
        boundsTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        boundsTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<BoundsRecord, String> boundsTypeColumn = new TableColumn<>("");
        boundsTypeColumn.setCellValueFactory(new PropertyValueFactory<>("boundsType"));

        TableColumn<BoundsRecord, String> xColumn = new TableColumn<>("MinX");
        xColumn.setCellValueFactory(new PropertyValueFactory<>("x"));

        TableColumn<BoundsRecord, String> yColumn = new TableColumn<>("MinY");
        yColumn.setCellValueFactory(new PropertyValueFactory<>("y"));

        TableColumn<BoundsRecord, String> wColumn = new TableColumn<>("width");
        wColumn.setCellValueFactory(new PropertyValueFactory<>("w"));

        TableColumn<BoundsRecord, String> hColumn = new TableColumn<>("height");
        hColumn.setCellValueFactory(new PropertyValueFactory<>("h"));

        boundsTableView.getColumns().setAll(boundsTypeColumn, xColumn, yColumn, wColumn, hColumn);

    }

    private static class VBoxUtil {
        public static VBox fromChildren(Node... children) {
            var vb = new VBox();
            vb.getChildren().addAll(children);
            return vb;
        }
    }

    /**
     * BoundsRecord
     */
    public class BoundsRecord {
        private final DoubleProperty x = new SimpleDoubleProperty();
        private final DoubleProperty y = new SimpleDoubleProperty();
        private final DoubleProperty w = new SimpleDoubleProperty();
        private final DoubleProperty h = new SimpleDoubleProperty();

        private final StringProperty boundsType = new SimpleStringProperty();

        public BoundsRecord(String boundsType) {
            this.boundsType.set(boundsType);
        }


        public BoundsRecord(String boundsType, Bounds bounds) {
            this.boundsType.set(boundsType);
            x.set(bounds.getMinX());
            y.set(bounds.getMinY());
            w.set(bounds.getWidth());
            h.set(bounds.getHeight());
        }

        public void update(Bounds bounds) {
            x.set(bounds.getMinX());
            y.set(bounds.getMinY());
            w.set(bounds.getWidth());
            h.set(bounds.getHeight());
        }

        public double getX() {
            return x.get();
        }

        public DoubleProperty xProperty() {
            return x;
        }

        public double getY() {
            return y.get();
        }

        public DoubleProperty yProperty() {
            return y;
        }

        public double getW() {
            return w.get();
        }

        public DoubleProperty wProperty() {
            return w;
        }

        public double getH() {
            return h.get();
        }

        public DoubleProperty hProperty() {
            return h;
        }

        public String getBoundsType() {
            return boundsType.get();
        }

        public StringProperty boundsTypeProperty() {
            return boundsType;
        }
    }
}
