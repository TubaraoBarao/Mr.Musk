package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame{

    public Main(){
        setTitle("Mr. Musk");
        setSize(1920, 1080);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        Panel panelGame = new Panel();
        add(panelGame);
        addWindowListener(new WindowAdapter(){
            public void windowOpened(WindowEvent e){
                panelGame.start();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main main = new Main();
            main.setVisible(true);
        });
    }
    
}