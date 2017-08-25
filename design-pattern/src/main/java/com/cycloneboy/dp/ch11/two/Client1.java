package com.cycloneboy.dp.ch11.two;

import java.util.ArrayList;

/**
 * Created by CycloneBoy on 2017/8/25.
 */
public class Client1 {
    public static void main(String[] args) {
        ArrayList<String> sequence = new ArrayList<String>();
        sequence.add("engine boom");
        sequence.add("start");
        sequence.add("stop");
        BenzBuilder benzBuilder = new BenzBuilder();
        benzBuilder.setSequence(sequence);
        BenzModel benz = (BenzModel)benzBuilder.getCarModel();
        benz.run();

        //按照同样的顺序，我要一个宝马
        BMWBuilder bmwBuilder = new BMWBuilder();
        bmwBuilder.setSequence(sequence);
        BMWModel bmw = (BMWModel)bmwBuilder.getCarModel();
        bmw.run();
    }
}
