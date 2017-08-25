package com.cycloneboy.dp.ch11.one;

import com.cycloneboy.dp.ch11.two.BenzModel;

import java.util.ArrayList;

/**
 * Created by CycloneBoy on 2017/8/25.
 */
public class Client {
    public static void main(String[] args) {
        BenzModel benz = new BenzModel();
        ArrayList<String> sequence = new ArrayList<String>();
        sequence.add("engine boom");
        sequence.add("start");
        sequence.add("stop");
        benz.setSequence(sequence);
        benz.run();
    }
}
