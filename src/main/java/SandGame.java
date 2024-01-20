import javafx.stage.Stage;

public class SandGame {

    private Particle[][] grid;


    public static void main(String[] args) {
        Display mainWindow = new Display();
        mainWindow.start(new Stage());
    }
    public SandGame(int width, int height) {
        grid = new Particle[width][height];
        //window = new Display(width, height);

    }

//    private void drawParticle(Particle p){
//        Graphics2D g = new Graphics2D();
//    }

    private void update(){
        for (Particle[] row : grid){
            for (Particle p :  row){
                if (p != null) {
                    p.update(grid);
                }
            }
        }
    }

    private void run(){
        boolean quit = false;
        while(!quit){
            update();
        }
    }
}
