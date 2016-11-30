package wiring.java;

import wiring.CD;

public class CDPlayer {

    private CD cd;

    public CDPlayer(CD cd) {
        this.cd = cd;
    }

    public void play() {
        cd.play();
    }
}
