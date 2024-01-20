import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.layout.ColumnConstraints;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class Display extends Application {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage){

        // Assuming 'pane' is your GridPane
        GridPane pane = new GridPane();
        pane.setGridLinesVisible(false);
        pane.setHgap(50); // Horizontal gap between grid cells
        pane.setVgap(0); // Vertical gap between grid cells
        for (int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(20);
            if (i < 4) {
                column.setHalignment(HPos.LEFT);
            } else {
                column.setHalignment(HPos.RIGHT);
            }
            pane.getColumnConstraints().add(column);
        }

        // Create our main functionality buttons
        Button clear = new Button("CLEAR");
        pane.add(clear, 4, 0);
        GridPane.setHalignment(clear, HPos.RIGHT);

        Button start = new Button("START");
        pane.add(start, 4, 1);
        GridPane.setHalignment(start, HPos.RIGHT);


        Button pause = new Button("PAUSE");
        pane.add(pause, 4, 2);
        GridPane.setHalignment(pause, HPos.RIGHT);

        // Radio buttons for material selection
        ToggleGroup tg = new ToggleGroup();
        RadioButton sand = new RadioButton("Sand");
        sand.setToggleGroup(tg);
        pane.add(sand, 1, 0);
        GridPane.setHalignment(sand, HPos.LEFT);

        RadioButton water = new RadioButton("Water");
        water.setToggleGroup(tg);
        pane.add(water, 2, 0);
        GridPane.setHalignment(water, HPos.LEFT);

        RadioButton stone = new RadioButton("Stone");
        stone.setToggleGroup(tg);
        pane.add(stone, 3, 0);
        GridPane.setHalignment(stone, HPos.LEFT);

        RadioButton wood = new RadioButton("Wood");
        wood.setToggleGroup(tg);
        pane.add(wood, 1, 1);
        GridPane.setHalignment(wood, HPos.LEFT);

        RadioButton fire = new RadioButton("Fire");
        fire.setToggleGroup(tg);
        pane.add(fire, 2, 1);
        GridPane.setHalignment(fire, HPos.LEFT);

        RadioButton tbd = new RadioButton("TBD");
        tbd.setToggleGroup(tg);
        pane.add(tbd, 3, 1);
        GridPane.setHalignment(tbd, HPos.LEFT);

        // Slider for stroke size selection
        Slider strokeSlider = new Slider(0, 100, 25);
        strokeSlider.setShowTickLabels(true);
        strokeSlider.setMajorTickUnit(25);
        strokeSlider.setMinorTickCount(5);
        strokeSlider.setShowTickMarks(true);
        pane.add(strokeSlider, 0, 1);

        // Slider label && Text Alignment
        Text strokeSliderLabel = new Text("Stroke Size");
        strokeSliderLabel.setTextAlignment(TextAlignment.CENTER);
        pane.add(strokeSliderLabel, 0, 0);
        GridPane.setHalignment(strokeSliderLabel, HPos.CENTER);


        Scene scene = new Scene (pane, WIDTH, HEIGHT);
        primaryStage.setTitle("SandScape");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
