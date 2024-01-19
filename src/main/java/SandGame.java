import java.awt.*;
import java.util.ArrayList;

public class SandGame {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private Particle[][] grid;


    public static void main(String[] args) {


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
