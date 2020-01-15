package agh.cs.snake;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;

class Game
{
    Game() throws IOException, ParseException
    {
        Object obj = new JSONParser().parse(new FileReader("data.json"));
        JSONObject jo = (JSONObject) obj;
        long width = (long) jo.get("width");
        long height = (long) jo.get("height");

        EventQueue.invokeLater(() -> {
            //Frame ex = new Frame((int)width,(int)height);
            //ex.setVisible(true);
            StartingScreen ex = new StartingScreen((int)width,(int)height);
            ex.setVisible(true);
        });
    }
}
