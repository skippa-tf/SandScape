public class Engine {
    Particle[][] grid;
    Display display;
    private double step = 0;
    private boolean gridChanged = false;
    private final int cellSize = 10;
    private int width =  (int)(Display.WIDTH / cellSize);
    private int height = (int)(Display.HEIGHT / cellSize);


    public Engine(){
        grid = new Particle[height][width];
    }

    public void setDisplay(Display d){
        display = d;
    }

    public int getCellSize(){
        return cellSize;
    }




    public void drawToGrid(int row, int col, int particleType){
        // add the particle to the particle grid
        row = row / cellSize;
        col = col / cellSize;
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

    public void drawGrid(Particle[][] g){
        for (Particle[] row : g){
            for (Particle p : row){
                if (p != null){
                    display.drawParticle(p);
                }
            }
        }
    }

    private void update() {
        gridChanged = false;
        //display.drawBlack();
        Particle[][] nextGrid = new Particle[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                Particle p = grid[i][j];
                if (p != null){
                    p.update(nextGrid);
                }
            }
        }

        grid = nextGrid;
        display.drawBlack();
        drawGrid(grid);



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
