package agh.cs.snake;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingScreen extends JFrame
{
    public StartingScreen(int width, int height)
    {
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200,200);
        JPanel panel = new JPanel();
        JButton play = new JButton("PLAY");
        panel.add(play);
        add(panel);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Frame ex = new Frame(width,height);
                ex.setVisible(true);
                dispose();
            }
        });
    }
}
