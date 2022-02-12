package edu.neumont.csc150;

import java.io.IOException;

public class Play {
    public static void main(String[] args) throws IOException, InterruptedException {
        GameController gc = new GameController();
        gc.run();
    }
}
