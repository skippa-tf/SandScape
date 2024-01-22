import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.canvas.*;
import javafx.scene.paint.*;

import javafx.beans.value.ChangeListener;



public class Display extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private int brushSize = 25;
    private Color brushType = Color.LIGHTGRAY;
    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage){

        // Assuming 'pane' is your GridPane
        GridPane controlsPane = new GridPane();
        controlsPane.setGridLinesVisible(false);
        controlsPane.setHgap(20); // Horizontal gap between grid cells
        controlsPane.setVgap(0); // Vertical gap between grid cells
        for (int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(20);
            controlsPane.getColumnConstraints().add(column);
        }

        // Create our main functionality buttons
        Button clear = new Button("CLEAR");
        controlsPane.add(clear, 4, 2);
        GridPane.setHalignment(clear, HPos.RIGHT);

        Button start = new Button("START");
        controlsPane.add(start, 4, 0);
        GridPane.setHalignment(start, HPos.RIGHT);


        Button pause = new Button("PAUSE");
        controlsPane.add(pause, 4, 1);
        GridPane.setHalignment(pause, HPos.RIGHT);

        // Radio buttons for material selection
        ToggleGroup tg = new ToggleGroup();

        RadioButton stone = new RadioButton("Stone");
        stone.setToggleGroup(tg);
        stone.setSelected(true);
        controlsPane.add(stone, 1, 0);
        stone.setOnAction(
                new EventHandler<>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        brushType = Color.LIGHTGRAY;
                        System.out.println("stone selected");
                    }
                }
        );

        RadioButton sand = new RadioButton("Sand");
        sand.setToggleGroup(tg);
        controlsPane.add(sand, 2, 0);
        sand.setOnAction(
                new EventHandler<>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        brushType = Color.SANDYBROWN;
                        System.out.println("sand selected");
                    }
                }
        );

        RadioButton water = new RadioButton("Water");
        water.setToggleGroup(tg);
        controlsPane.add(water, 3, 0);
        water.setOnAction(
                new EventHandler<>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        brushType = Color.MEDIUMBLUE;
                        System.out.println("water selected");
                    }
                }
        );

        // Todo implement these materials properly
        RadioButton wood = new RadioButton("TBD");
        wood.setDisable(true);
        wood.setToggleGroup(tg);
        controlsPane.add(wood, 1, 1);

        RadioButton fire = new RadioButton("TBD");
        fire.setDisable(true);
        fire.setToggleGroup(tg);
        controlsPane.add(fire, 2, 1);

        RadioButton tbd = new RadioButton("TBD");
        tbd.setDisable(true);
        tbd.setToggleGroup(tg);
        controlsPane.add(tbd, 3, 1);

        // Slider label && Text Alignment
        Text strokeSliderLabel = new Text("Stroke Size: " + brushSize);
        strokeSliderLabel.setTextAlignment(TextAlignment.CENTER);
        controlsPane.add(strokeSliderLabel, 0, 0);
        GridPane.setHalignment(strokeSliderLabel, HPos.CENTER);

        // Slider for stroke size selection
        Slider strokeSlider = new Slider(0, 50, brushSize);
        strokeSlider.setShowTickLabels(true);
        strokeSlider.setMajorTickUnit(10);
        strokeSlider.setShowTickMarks(true);
        controlsPane.add(strokeSlider, 0, 1);
        strokeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            // I got this code from: https://stackoverflow.com/a/22781451/23110483
            // I don't know exactly what is going on here.
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                brushSize = newValue.intValue();
                strokeSliderLabel.setText("Stroke Size: " + brushSize);
            }
        });


        // Create a black painting canvas.
        Canvas canvas = new Canvas(WIDTH, HEIGHT); // TODO Make it scale to the window properly, otherwise the pixels will continue to fall off the screen, to the bottom of the unseen canvas.
        canvas.setStyle("-fx-background-color: black");
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.setOnMouseDragged(
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        double x = mouseEvent.getX();
                        double y = mouseEvent.getY();
                        gc.setFill(brushType);
                        gc.fillOval(x, y, brushSize, brushSize);
                    }
                }
        );
        controlsPane.add(canvas, 0, 4, 4, 1);


        Scene scene = new Scene (controlsPane, WIDTH, HEIGHT);
        primaryStage.setTitle("SandScape");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public double getBrushSize(){
        return brushSize;
    }




}
