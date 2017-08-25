package com.cycloneboy.dp.ch11.two;

import com.cycloneboy.dp.ch11.one.CarBuilder;
import com.cycloneboy.dp.ch11.one.CarModel;

import java.util.ArrayList;

/**
 * Created by CycloneBoy on 2017/8/25.
 */
public class BMWBuilder extends CarBuilder {
    private BMWModel bmw = new BMWModel();
    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.bmw.setSequence(sequence);
    }

    @Override
    public CarModel getCarModel() {
        return this.bmw;
    }
}
