package wiring.mix;

import wiring.CD;

public class Apologize implements CD {

    @Override
    public void play() {
        System.out.println("Playing Apologize");
    }
}
