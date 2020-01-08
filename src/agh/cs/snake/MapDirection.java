package agh.cs.snake;

public enum MapDirection
{
    NORTH,
    WEST,
    SOUTH,
    EAST;
    public Vector2d toUnitVector()
    {
        switch (this)
        {
            case NORTH: return new Vector2d(0,-1);
            case WEST: return new Vector2d(-1,0);
            case SOUTH: return new Vector2d(0,1);
            default: return new Vector2d(1,0);
        }
    }
}
