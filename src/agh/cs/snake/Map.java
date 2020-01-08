package agh.cs.snake;

import java.util.LinkedList;
import java.util.List;

public class Map
{
    int width;
    int height;
    public Snake snake;
    Apple apple;
    List<Stone> stones=new LinkedList<>();
    Map(int width, int height)
    {
        this.width=width;
        this.height=height;
    }
    void addSnake()
    {
        this.snake=new Snake(this);
    }
    void addApple()
    {
        int x=(int)(Math.random()*width);
        int y=(int)(Math.random()*height);
        Vector2d pos=new Vector2d(x,y);
        while(isOccupied(pos))
        {
            x=(int)(Math.random()*width);
            y=(int)(Math.random()*height);
            pos=new Vector2d(x,y);
        }
        this.apple=new Apple(pos);
    }
    void addStones()
    {
        int n=this.width*this.height/100;
        for(int i=0; i<n; i++)
        {
            int x=(int)(Math.random()*width);
            int y=(int)(Math.random()*height);
            Vector2d pos=new Vector2d(x,y);
            while(isOccupied(pos))
            {
                x=(int)(Math.random()*width);
                y=(int)(Math.random()*height);
                pos=new Vector2d(x,y);
            }
            this.stones.add(new Stone(pos));
        }
    }
    private boolean isOccupied(Vector2d position)
    {
        for(Vector2d comp : this.snake.components)
        {
            if(comp.equals(position))
                return true;
        }
        for(Stone sto : this.stones)
        {
            if(sto.position.equals(position))
                return true;
        }
        return false;
    }
    boolean isEating()
    {
        return this.snake.components.get(0).equals(apple.position);
    }
    void eat()
    {
        this.snake.hasEaten=true;
        this.addApple();
    }
    boolean isColliding()
    {
        for(int i=1; i<snake.components.size(); i++)
        {
            if(snake.components.get(i).equals(snake.components.get(0)))
                return true;
        }
        int n=this.width*this.height/100;
        for(int i=0; i<n; i++)
        {
            if(stones.get(i).position.equals(snake.components.get(0)))
                return true;
        }
        return !snake.components.get(0).precedes(new Vector2d(width, height)) || !snake.components.get(0).follows(new Vector2d(-1, -1));
    }
    void change(MapDirection direction)
    {
        Vector2d position=direction.toUnitVector();
        snake.components.add(0,snake.components.get(0).add(position));
        snake.mapDirs.add(0,direction);
        if(!this.snake.hasEaten)
        {
            snake.components.remove(snake.components.size()-1);
            snake.mapDirs.remove(snake.mapDirs.size()-1);
        }

        snake.hasEaten=false;
    }
}
