package agh.cs.snake;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Frame extends JFrame
{
    public int width;
    public int height;
    public int highScore;
    public String name;
    public String champion;
    Frame(int width, int height, int highScore, String name, String champion)
    {
        this.height=height;
        this.width=width;
        this.highScore=highScore;
        this.name=name;

        add(new Panel(width, height, highScore, name, champion));

        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
}
