package agh.cs.snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Adapter extends KeyAdapter
{
    boolean leftDirection = false;
    boolean rightDirection = false;
    boolean upDirection = true;
    boolean downDirection = false;
    @Override
    public void keyPressed(KeyEvent e)
    {
        int key=e.getKeyCode();

        if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
            leftDirection = true;
            upDirection = false;
            downDirection = false;
        }

        if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
            rightDirection = true;
            upDirection = false;
            downDirection = false;
        }

        if ((key == KeyEvent.VK_UP) && (!downDirection)) {
            upDirection = true;
            rightDirection = false;
            leftDirection = false;
        }

        if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
            downDirection = true;
            rightDirection = false;
            leftDirection = false;
        }

    }

}
