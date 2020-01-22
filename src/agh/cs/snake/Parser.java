package agh.cs.snake;

import org.json.simple.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Parser
{
    public Parser(int highScore, String champion) throws FileNotFoundException {
        JSONObject jo = new JSONObject();
        jo.put("highScore", highScore);
        jo.put("champion", champion);
        PrintWriter pw = new PrintWriter("highScore.json");
        pw.write(jo.toJSONString());
        pw.flush();
        pw.close();
    }
}
