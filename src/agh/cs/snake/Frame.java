package agh.cs.snake;

import javax.swing.*;

class Frame extends JFrame
{
    Frame(int width, int height)
    {
        add(new Panel(width, height));

        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
