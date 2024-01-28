public class Engine {
    Particle[][] grid;
    Display display;
    private double step = 0;
    private boolean gridChanged = false;
    private double cellSize = 10;
    private int width =  (int)(Display.WIDTH / cellSize);
    private int height = (int)(Display.HEIGHT / cellSize);


    public Engine(){
        grid = new Particle[Display.WIDTH][Display.HEIGHT];
    }

    public void setDisplay(Display d){
        display = d;
    }




    public void drawToGrid(int x, int y, Particle p){
        if (x < grid.length && y < grid[x].length && grid[x][y] == null){
            p.setRow(x);
            p.setCol(y);
            grid[x][y] = p;
            gridChanged = true;
        }
    }

    private void update() {
        gridChanged = false;
        display.drawBlack();
        for (Particle[] row : grid){
            for (Particle p :  row){
                if (p != null) {
                    System.out.println("inside loop");
                    p.update(grid);
                    display.drawParticle(p);
                }
            }
        }
    }

    public void run(){
        boolean quit = false;
        while(!quit){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            step += 100;
            if (gridChanged) {
                System.out.println("drawing black");

                update();
            }

        }
    }
}
