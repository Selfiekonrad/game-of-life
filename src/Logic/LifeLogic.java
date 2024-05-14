package Logic;

import java.util.Random;

public class LifeLogic {
    private final int numberOfCellsInX;
    private final int numberOfCellsInY;
    private boolean[][] livingCells;
    private boolean[][] livingCellsBuffer;

    public LifeLogic(int maxScreenX, int maxScreenY) {
        this.numberOfCellsInX = maxScreenX;
        this.numberOfCellsInY = maxScreenY;
        livingCells = new boolean[maxScreenX][maxScreenY];
        livingCellsBuffer = new boolean[maxScreenX][maxScreenY];
        generateFirstWorld();
    }

    public void update() {
        generateNextWorld();
    }

    public boolean[][] getLivingCells() {
        return livingCells;
    }

    private void generateFirstWorld() {
        Random random = new Random();
        for (int y = 0; y < numberOfCellsInY; y++) {
            for (int x = 0; x < numberOfCellsInX; x++) {
                if (random.nextInt(8) == 0) livingCells[x][y] = true;
            }
        }
    }

    private void generateNextWorld() {
        for (int y = 0; y < numberOfCellsInY; y++) {
            for (int x = 0; x < numberOfCellsInX; x++) {
                final int neighbors = calculateHowManyNeighbours(x, y);

                if (neighbors < 2 && livingCells[x][y]) livingCellsBuffer[x][y] = false;
                if ((neighbors == 2 || neighbors == 3) && livingCells[x][y]) livingCellsBuffer[x][y] = true;
                if (neighbors > 3 && livingCells[x][y]) livingCellsBuffer[x][y] = false;
                if (neighbors == 3  && !livingCells[x][y]) livingCellsBuffer[x][y] = true;
            }
        }
        livingCells = livingCellsBuffer;
        livingCellsBuffer = new boolean[numberOfCellsInX][numberOfCellsInY];
    }

    private int calculateHowManyNeighbours(int xCell, int yCell) {
        int neigboursCount = 0;
        for (int y = -1; y <= 1 ; y++) {
            for (int x = -1; x <= 1; x++) {
                int newX = xCell + x;
                int newY = yCell + y;
                if (newX >=0 && newX < numberOfCellsInX && newY >= 0 && newY < numberOfCellsInY && livingCells[newX][newY] && !(x == 0 && y == 0)) {
                    neigboursCount++;
                }
            }
        }
        return neigboursCount;
    }
}
