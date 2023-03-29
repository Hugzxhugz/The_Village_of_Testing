package org.example;
import java.util.Random;

public class Randomizer {
    Random random = new Random();
    public int GetRandomInt(int bound)
    {
        return random.nextInt(bound);
    }

}
