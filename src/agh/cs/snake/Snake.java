package agh.cs.snake;

import java.util.LinkedList;
import java.util.List;

class Snake
{
    List<Vector2d> components=new LinkedList<>();
    List<MapDirection> mapDirs=new LinkedList<>();
    private Map map;
    boolean hasEaten=false;
    Snake(Map map)
    {
        this.map=map;
        int x=(this.map.width/2);
        int y=(this.map.height/2);
        this.components.add(new Vector2d(x,y));
        this.components.add(new Vector2d(x,y+1));
        this.mapDirs.add(MapDirection.NORTH);
        this.mapDirs.add(MapDirection.NORTH);
    }
}
