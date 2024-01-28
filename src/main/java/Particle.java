import javafx.scene.paint.Color;

/**
 * Reference: https://github.com/kristiehuang/Falling-Sand/blob/master/SandLab.java
 */

public class Particle {
    private String name;
    private static double totalParticles = 0;
    private int row;
    private int col;
    private int oldRow;
    private int oldCol;
    private Color color;

    public static final String WATER = "WATER";
    public static final String SAND = "SAND";
    public static final String STONE = "STONE";

    public Particle(String name, Color c) {
        this.name = name;
        this.color = c;
        totalParticles++;
    }

    public void update(Particle[][] grid) {
        oldRow = row;
        oldCol = col;
        int newRow = row + 1;
        if (newRow < grid.length && grid[newRow][col] == null) {
            row++;
        } else if (newRow < grid.length) {
            boolean moveDiag = false;
            if (col - 1 >= 0 && grid[row + 1][col - 1] == null) {
                col--;
                moveDiag = true;
            } else if (col + 1 < grid[row + 1].length && grid[newRow][col + 1] == null) {
                col++;
                moveDiag = true;
            }
            if (moveDiag) {
                row++;
            }

        }
        if (row != oldRow || col != oldCol) {
            grid[row][col] = this;
            grid[oldRow][oldCol] = null;
        }
    }



    public void draw() {

    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getOldRow() {
        return oldRow;
    }

    public int getOldCol() {
        return oldCol;
    }



    public String getName() {
        return name;
    }

    public double getTotalParticles() {
        return totalParticles;
    }

    public Color getColor() {
        return color;
    }
}

class Water extends Particle {
    private static int particleCount = 0;

    public Water() {
        super(WATER, Color.BLUE);
        particleCount++;
    }

    public int getParticleCount() {
        return particleCount;
    }
}

class Sand extends Particle {
    private static int particleCount = 0;

    public Sand() {
        super(SAND, Color.LIGHTGOLDENRODYELLOW);
        particleCount++;
    }

    public int getParticleCount() {
        return particleCount;
    }
}

class Stone extends Particle {
    private static int particleCount = 0;

    public Stone() {
        super(STONE, Color.LIGHTGRAY);
        particleCount++;
    }

    @Override
    public void update(Particle[][] grid) {
        return;
    }

    public int getParticleCount() {
        return particleCount;
    }
}
