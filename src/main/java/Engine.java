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




    public void drawToGrid(int row, int col, int particleType){
        Particle p = new Particle(null, null, 0, 0);
        switch (particleType){
            case 0:
                p = new Stone(row, col);
                break;
            case 1:
                p = new Sand(row, col);
                break;
            case 2:
                p = new Water(row, col);
                break;
        }
        if (row < grid.length && col < grid[row].length && grid[row][col] == null){
            grid[row][col] = p;
            gridChanged = true;
        }
    }

    private void update() {
        gridChanged = false;
        //display.drawBlack();
        for (Particle[] row : grid){
            for (Particle p :  row){
                if (p != null && p.hasMoved()) {
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
