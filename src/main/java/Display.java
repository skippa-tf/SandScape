import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.PixelWriter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class Display extends Application {
    protected static final int WIDTH = 800;
    protected static final int HEIGHT = 600;
    private int brushSize = 1;
    private int particleType = 0; // 0: Stone, 1: Sand, 2: Water
    private Canvas canvas;
    private GraphicsContext gc;
    private PixelWriter pw;
    private static Engine engine;

    public Display(){}
    public static void setEngine(Engine e){
        engine = e;
    }

    public void initDisplay(){
        engine.setDisplay(this);
        new Thread(engine::run).start(); // run the engine on a different thread from the ui.
    }
    @Override
    public void start(Stage primaryStage) {

        // Assuming 'pane' is your GridPane
        GridPane controlsPane = new GridPane();
        controlsPane.setGridLinesVisible(false);
        controlsPane.setHgap(20); // Horizontal gap between grid cells
        controlsPane.setVgap(0); // Vertical gap between grid cells
        // Setup column widths
        for (int i = 0; i < 5; i++) {
            ColumnConstraints column = new ColumnConstraints();
            column.setPercentWidth(20);
            controlsPane.getColumnConstraints().add(column);
        }

        // Create our main functionality buttons
        Button clear = new Button("CLEAR");
        controlsPane.add(clear, 4, 2);
        GridPane.setHalignment(clear, HPos.RIGHT);
        clear.setOnAction(
                actionEvent -> {
                    GraphicsContext gc = canvas.getGraphicsContext2D();
                    gc.setFill(Color.BLACK);
                    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    engine.grid = new Particle[engine.grid.length][engine.grid[0].length];
                }
        );

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
                actionEvent -> {
                    particleType = 0;
                }
        );

        RadioButton sand = new RadioButton("Sand");
        sand.setToggleGroup(tg);
        controlsPane.add(sand, 2, 0);
        sand.setOnAction(
                actionEvent -> {
                    particleType = 1;
                }
        );

        RadioButton water = new RadioButton("Water");
        water.setToggleGroup(tg);
        controlsPane.add(water, 3, 0);
        water.setOnAction(
                actionEvent -> {
                    particleType = 2;
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
        // TODO Make it scale to the window properly, otherwise the pixels will continue to fall off the screen, to the bottom of the unseen canvas.
        canvas = new Canvas(WIDTH, HEIGHT);
        canvas.setStyle("-fx-background-color: black");
        gc = canvas.getGraphicsContext2D();
        // pw is used to draw individual pixels.
        pw = gc.getPixelWriter();

        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        // Set the same EventHandler for both "clicking" the mouse and "dragging" the mouse.
        canvas.setOnMousePressed(new drawOnCursor());
        canvas.setOnMouseDragged(new drawOnCursor());
        controlsPane.add(canvas, 0, 4, 4, 1);


        Scene scene = new Scene(controlsPane, WIDTH, HEIGHT);
        primaryStage.setTitle("SandScape");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Startup game engine after JavaFX application is set up.
        Platform.runLater(() -> {
            initDisplay();
        });
    }
    public void drawBlack(){
        if (gc != null) {
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        }
    }

    public void drawParticle(Particle p){
        pw.setColor(p.getOldCol(), p.getOldRow(), Color.BLACK); // set the previous spot back to black.
        pw.setColor( p.getCol(), p.getRow(),p.getColor());
    }

    public double getBrushSize() {
        return brushSize;
    }

    public GraphicsContext getCanvasGraphicsContext(){
        return canvas.getGraphicsContext2D();
    }

    public PixelWriter getPw() {
        return pw;
    }


    class drawOnCursor implements EventHandler<MouseEvent> {
        @Override
        public void handle(MouseEvent mouseEvent) {
            // Set the center to the mouse.
            int col = (int)(mouseEvent.getX() -  brushSize);
            int row = (int)(mouseEvent.getY() -  brushSize);
            System.out.println("drawing at x:" + col + " y:" + row);
            engine.drawToGrid(row, col, particleType);
        }
    }
}