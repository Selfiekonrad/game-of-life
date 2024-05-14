package GUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        LifePanel panel =  new LifePanel();
        window.add(panel);

        window.pack();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        panel.startGameThread();
    }
}