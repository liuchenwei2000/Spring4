package wiring.java;

import wiring.CD;

public class WhiteAlbum implements CD {

    @Override
    public void play() {
        System.out.println("Playing WhiteAlbum");
    }
}
