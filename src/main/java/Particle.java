/**
 * Reference: https://github.com/kristiehuang/Falling-Sand/blob/master/SandLab.java
 */

public class Particle {
    private String name;
    private static double totalParticles = 0;
    private int row;
    private int col;

    public static final String WATER = "WATER";
    public static final String SAND = "SAND";
    public static final String STONE = "STONE";

    public Particle(String name){
        this.name = name;
        totalParticles++;
    }

    public void update(Particle[][] grid){
        int newRow = row + 1;
        if (newRow < grid.length){
            if (grid[newRow][col] == null) {
                row++;
            } else {
                if (col - 1 >= 0 && grid[newRow][col -  1] == null){
                    col--;
                } else if (col + 1 < grid[newRow].length && grid[newRow][col + 1] == null){
                    col++;
                } else {
                    return;
                }
                row++;
            }
        }
    }

    public void setRow(int row){ this.row = row; }

    public void setCol(int col){ this.col = col; }

    public int getRow() { return row; }

    public int getCol() {
        return col;
    }

    public String getName() {
        return name;
    }

    public double getTotalParticles(){ return totalParticles; }
}

class Water extends Particle {
    private static int particleCount = 0;

    public Water(){
        super(WATER);
        particleCount++;
    }

    public int getParticleCount(){
        return particleCount;
    }
}

class Sand extends Particle {
    private static int particleCount = 0;

    public Sand(){
        super(SAND);
        particleCount++;
    }
    public int getParticleCount(){
        return particleCount;
    }
}

class Stone extends Particle {
    private static int particleCount = 0;

    public Stone(){
        super(STONE);
        particleCount++;
    }

    public int getParticleCount(){
        return particleCount;
    }
}
