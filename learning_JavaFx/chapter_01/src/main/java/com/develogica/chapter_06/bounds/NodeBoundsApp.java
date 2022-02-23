package com.develogica.chapter_06.bounds;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Bounds;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;

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

    }

    private void attachEventHandlers() {

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
        transformBox.getChildren().addAll(transformBox, rotateBox, scaleBox, shearBox);
        TitledPane transformProps = new TitledPane("Transformations", transformBox);

        TitledPane showBoundsControls = getShowBoundsControls();
        TitledPane effectPane = getEffectTitledPane();

        Button resetAllButton = new Button();
        resetAllButton.setOnAction(event -> resetAll());

        Button saveButton = new Button();
        saveButton.setOnAction(event -> saveLayoutAsImage());

        Button exitButton = new Button();
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

    }

    private TitledPane getShowBoundsControls() {
        return null;
    }

    private TitledPane getEffectTitledPane() {
        return null;
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

    private void initializeBoundsPathTransition() {

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
