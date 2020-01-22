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

        Object his = new JSONParser().parse(new FileReader("highScore.json"));
        JSONObject hs = (JSONObject) his;
        long highScore = (long) hs.get("highScore");
        String champion = (String) hs.get("champion");
        EventQueue.invokeLater(() -> {
            StartingScreen ex = new StartingScreen((int)width,(int)height,(int)highScore, champion);
            ex.setVisible(true);
        });
    }
}
