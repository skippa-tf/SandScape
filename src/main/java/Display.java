import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Display extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }


    @Override
    public void start(Stage primaryStage){

        // Create our main functionality buttons.
        Button clear = new Button("CLEAR");
        Button start = new Button("START");
        Button pause = new Button("PAUSE");

        // Radio buttons for material selection.
        ToggleGroup tg = new ToggleGroup();

        RadioButton stone = new RadioButton("Stone");
        stone.setSelected(true);
        stone.setToggleGroup(tg);

        RadioButton water = new RadioButton("Water");
        water.setToggleGroup(tg);

        RadioButton sand = new RadioButton("Sand");
        sand.setToggleGroup(tg);

        RadioButton tbd = new RadioButton("TBD");
        tbd.setToggleGroup(tg);

        RadioButton tbdTwo = new RadioButton("TBD");
        tbdTwo.setToggleGroup(tg);

        RadioButton tbdThree = new RadioButton("TBD");
        tbdThree.setToggleGroup(tg);

        // A slider for stroke size selection.
        Slider strokeSlider = new Slider(0, 100, 25);
        strokeSlider.setShowTickLabels(true);
        strokeSlider.setMajorTickUnit(25);
        strokeSlider.setMinorTickCount(5);
        strokeSlider.setShowTickMarks(true);

        // Add all our controls to a new pane.
        FlowPane pane = new FlowPane();
        pane.getChildren().addAll(clear, start, pause, stone, water, sand, tbd, tbdTwo, tbdThree, strokeSlider);

        Scene scene = new Scene (pane, 800, 600);
        primaryStage.setTitle("SandScape");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
