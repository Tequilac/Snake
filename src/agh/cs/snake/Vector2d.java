package agh.cs.snake;

public class Vector2d
{
    private int x;
    private int y;
    Vector2d(int x, int y)
    {
        this.x=x;
        this.y=y;
    }
    boolean equals(Vector2d other)
    {
        return (this.x==other.x) && (this.y==other.y);
    }
    boolean precedes(Vector2d other)
    {
        return (this.x<other.x) && (this.y<other.y);
    }
    boolean follows(Vector2d other)
    {
        return (this.x>other.x) && (this.y>other.y);
    }
    Vector2d add(Vector2d other)
    {
        return new Vector2d(this.x+other.x,this.y+other.y);
    }
    int getX()
    {
        return this.x;
    }
    int getY()
    {
        return this.y;
    }
    public String toString ()
    {
        return this.x+" "+this.y;
    }
}
