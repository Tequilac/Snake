package agh.cs.snake;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Frame extends JFrame
{
    public int width;
    public int height;
    Frame(int width, int height)
    {
        this.height=height;
        this.width=width;

        add(new Panel(width, height));

        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    
}
