package GUI;

import Logic.LifeLogic;

import javax.swing.*;
import java.awt.*;

public class LifePanel extends JPanel implements Runnable {

    private final int originalTileSize = 6;
    private final int scale = 1;

    private final int tileSize = originalTileSize * scale;
    private final int maxScreenX = 370;
    private final int maxScreenY = 170;

    Thread thread;
    LifeLogic lifeLogic;

    public LifePanel() {
        this.setBackground(Color.black);
        int screenWidth = maxScreenX * tileSize;
        int screenHeight = maxScreenY * tileSize;

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);

        lifeLogic = new LifeLogic(maxScreenX, maxScreenY);
    }

    public void startGameThread() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while (thread != null) {
            // update
            update();
            // draw
            repaint();

            try {
                thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.green);
        drawFirstWorld(g);
        g.dispose();
    }

    public void update() {
        lifeLogic.update();
    }

    private void drawFirstWorld(Graphics g) {
        boolean[][] liveCells = lifeLogic.getLivingCells();
        for (int i = 0; i < maxScreenY; i++) {
            for (int j = 0; j < maxScreenX; j++) {
                if (liveCells[j][i]) g.fillRect(j * tileSize, i * tileSize, tileSize, tileSize);
            }
        }
    }
}
