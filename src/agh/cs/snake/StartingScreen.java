package agh.cs.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingScreen extends JFrame
{
    public StartingScreen(int width, int height, int highScore, String champion)
    {
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200,300);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        JLabel nam=new JLabel("Enter your name:");
        panel.add(nam);
        JTextField name=new JTextField();
        name.setPreferredSize(new Dimension(150,20));
        panel.add(name);
        JButton play = new JButton("Start");
        JLabel [] rules = new JLabel[5];
        rules[0]=new JLabel("The rules are simple:");
        rules[1]=new JLabel("1.Eat apples to grow");
        rules[2]=new JLabel("2.Stones kill you");
        rules[3]=new JLabel("3.Walls kill you");
        rules[4]=new JLabel("4.You kill yourself");
        panel.add(play);
        for (int i=0; i<5; i++)
            panel.add(rules[i]);
        JLabel champ = new JLabel("Current champion: "+champion);
        panel.add(champ);
        add(panel);
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Frame ex = new Frame(width,height,highScore,name.getText(), champion);
                ex.setVisible(true);
                dispose();
            }
        });
    }
}
