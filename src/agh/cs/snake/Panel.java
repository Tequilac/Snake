package agh.cs.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;

public class Panel extends JPanel implements ActionListener
{
    public int width;
    public int height;
    public int highScore;
    public int score;
    public String name;
    public String champion;
    private Map map;
    private Adapter key;
    private Image apple;
    private Image stone;
    private Image head;
    private Image body;
    private Image tail;
    private Image bodyTwist;
    private Image sand;
    private JLabel scoreLabel;
    private JLabel highScoreLabel;
    private Color scoreColor = Color.white;
    private Font font = new Font("Alata", Font.PLAIN, 14);
    private BufferedImage bodyRot;
    private BufferedImage [] bodyTwistRot = new BufferedImage[3];
    private BufferedImage [] tailRot = new BufferedImage[3];
    private BufferedImage [] headRot = new BufferedImage[3];
    private boolean inGame=true;
    Panel(int width, int height, int highScore, String name, String champion)
    {
        this.width=width;
        this.height=height;
        this.highScore=highScore;
        this.name=name;
        this.champion=champion;
        this.score=0;
        this.scoreLabel = new JLabel("Your score: "+this.score);
        scoreLabel.setForeground(Color.white);
        scoreLabel.setBounds(0,height*20,width*10, 30);
        scoreLabel.setFont(font);
        this.add(scoreLabel);
        this.highScoreLabel = new JLabel("High score: "+this.highScore);
        highScoreLabel.setForeground(Color.white);
        highScoreLabel.setBounds(width*10,height*20,width*10, 30);
        highScoreLabel.setFont(font);
        this.add(highScoreLabel);
        key = new Adapter();
        addKeyListener(key);
        setBackground(Color.darkGray);
        setFocusable(true);

        setPreferredSize(new Dimension(width*20, height*20+30));
        ImageIcon app=new ImageIcon("src/resources/apple.png");
        apple=app.getImage();
        ImageIcon sto=new ImageIcon("src/resources/stone.png");
        stone=sto.getImage();
        ImageIcon hea=new ImageIcon("src/resources/head.png");
        head=hea.getImage();
        ImageIcon bod=new ImageIcon("src/resources/body.png");
        body= bod.getImage();
        ImageIcon tai=new ImageIcon("src/resources/tail.png");
        tail= tai.getImage();
        ImageIcon twi=new ImageIcon("src/resources/bodytwist.png");
        bodyTwist=twi.getImage();
        ImageIcon san=new ImageIcon("src/resources/sand.png");
        sand=san.getImage();
        BufferedImage bodRot = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bodRot.createGraphics();
        bGr.drawImage(body, 0, 0, null);
        bGr.dispose();
        AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians (90), 10, 10);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
        bodyRot=(op.filter(bodRot,null));
        for(int i=1; i<=3; i++)
        {
            BufferedImage bodTwiRot = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
            Graphics2D bGra1 = bodTwiRot.createGraphics();
            bGra1.drawImage(bodyTwist, 0, 0, null);
            bGra1.dispose();
            BufferedImage bodTaiRot = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
            Graphics2D bGra2 = bodTaiRot.createGraphics();
            bGra2.drawImage(tail, 0, 0, null);
            bGra2.dispose();
            BufferedImage bodHeaRot = new BufferedImage(20, 20, BufferedImage.TYPE_INT_ARGB);
            Graphics2D bGra3 = bodHeaRot.createGraphics();
            bGra3.drawImage(head, 0, 0, null);
            bGra3.dispose();
            AffineTransform tran = AffineTransform.getRotateInstance(Math.toRadians (90*i), 10, 10);
            AffineTransformOp opr = new AffineTransformOp(tran, AffineTransformOp.TYPE_BILINEAR);
            bodyTwistRot[i-1]=(opr.filter(bodTwiRot,null));
            tailRot[i-1]=(opr.filter(bodTaiRot,null));
            headRot[i-1]=(opr.filter(bodHeaRot,null));
        }

        this.map=new Map(width,height);
        this.map.addSnake();
        this.map.addStones();
        this.map.addApple();

        Timer timer=new Timer(200,this);
        timer.start();
    }
    public void showScore()
    {
        this.remove(scoreLabel);
        this.scoreLabel = new JLabel("Your score: "+this.score);
        scoreLabel.setForeground(scoreColor);
        scoreLabel.setBounds(0,height*20,width*10, 30);
        scoreLabel.setFont(font);
        this.add(scoreLabel);
        this.remove(highScoreLabel);
        this.highScoreLabel = new JLabel("High score: "+this.highScore);
        highScoreLabel.setForeground(Color.white);
        highScoreLabel.setBounds(width*10,height*20,width*10, 30);
        highScoreLabel.setFont(font);
        this.add(highScoreLabel);
    }
    public void addScore(int value)
    {
        this.score+=value;
        if(this.score>this.highScore)
        {
            this.highScore=this.score;
            this.scoreColor=Color.green;
            this.champion=this.name;
        }

    }

    public void paintComponent(Graphics g)
    {
        if(inGame)
        {
            super.paintComponent(g);
            for(int i=0; i<this.width; i++)
            {
                for(int j=0; j<this.height; j++)
                {
                    draw(g, sand, new Vector2d(i, j));
                }
            }
            draw(g, apple, this.map.apple.position);

            for(Stone stones : this.map.stones)
            {
                draw(g, stone, stones.position);
            }

            Vector2d headPosition = this.map.snake.components.get(0);
            switch (this.map.snake.mapDirs.get(0))
            {
                case NORTH: draw(g, head, headPosition);
                    break;
                case EAST: draw(g, headRot[0], headPosition);
                    break;
                case SOUTH: draw(g, headRot[1], headPosition);
                    break;
                case WEST: draw(g, headRot[2], headPosition);
            }


            int length=this.map.snake.components.size();
            for(int i=1; i<length-1; i++)
            {
                if(!this.map.snake.mapDirs.get(i).equals(this.map.snake.mapDirs.get(i-1)))
                {
                    if(this.map.snake.mapDirs.get(i).equals(this.map.snake.mapDirs.get(i-1).previous()))
                    {
                        switch (this.map.snake.mapDirs.get(i))
                        {
                            case NORTH: draw(g,bodyTwist,this.map.snake.components.get(i));
                                break;
                            case WEST: draw(g,bodyTwistRot[2],this.map.snake.components.get(i));
                                break;
                            case SOUTH: draw(g,bodyTwistRot[1],this.map.snake.components.get(i));
                                break;
                            case EAST: draw(g,bodyTwistRot[0],this.map.snake.components.get(i));
                                break;
                        }
                    }
                    else
                    {
                        switch (this.map.snake.mapDirs.get(i))
                        {
                            case NORTH: draw(g,bodyTwistRot[0],this.map.snake.components.get(i));
                                break;
                            case WEST: draw(g,bodyTwist,this.map.snake.components.get(i));
                                break;
                            case SOUTH: draw(g,bodyTwistRot[2],this.map.snake.components.get(i));
                                break;
                            case EAST: draw(g,bodyTwistRot[1],this.map.snake.components.get(i));
                                break;
                        }
                    }
                }
                else
                {
                    if(this.map.snake.mapDirs.get(i).equals(MapDirection.NORTH) || this.map.snake.mapDirs.get(i).equals(MapDirection.SOUTH))
                        draw(g,body,this.map.snake.components.get(i));
                    else
                        draw(g,bodyRot,this.map.snake.components.get(i));
                }

            }


            switch (this.map.snake.mapDirs.get(length-2))
            {
                case NORTH: draw(g, tail,this.map.snake.components.get(length-1));
                    break;
                case EAST: draw(g, tailRot[0],this.map.snake.components.get(length-1));
                    break;
                case SOUTH: draw(g, tailRot[1],this.map.snake.components.get(length-1));
                    break;
                case WEST: draw(g, tailRot[2],this.map.snake.components.get(length-1));
            }

            Toolkit.getDefaultToolkit().sync();
        }
        else
        {
            String msg = "Game Over";
            if(scoreColor==Color.green)
                msg="Congratulations, "+this.name+". You have beat the high score";
            Font small = new Font("Helvetica", Font.BOLD, 14);
            FontMetrics metrics = getFontMetrics(small);
            g.setColor(Color.black);
            g.setFont(small);
            g.drawString(msg, (getWidth() - metrics.stringWidth(msg)) / 2, getHeight() / 2);
            try {
                Parser parser=new Parser(this.highScore, this.champion);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void draw (Graphics graphics, Image image, Vector2d position)
    {
        graphics.drawImage(image, position.getX()*20,position.getY()*20, this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if(this.map.isEating())
        {
            this.map.eat();
            this.addScore(100000/(this.height*this.width));
        }


        if(key.leftDirection)
            map.change(MapDirection.WEST);
        if(key.rightDirection)
            map.change(MapDirection.EAST);
        if(key.upDirection)
            map.change(MapDirection.NORTH);
        if(key.downDirection)
            map.change(MapDirection.SOUTH);

        if(map.isColliding())
            inGame=false;
        showScore();
        repaint();
    }
}
