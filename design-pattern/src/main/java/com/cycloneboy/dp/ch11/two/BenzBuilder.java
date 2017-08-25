package com.cycloneboy.dp.ch11.two;

import com.cycloneboy.dp.ch11.one.CarBuilder;
import com.cycloneboy.dp.ch11.one.CarModel;

import java.util.ArrayList;

/**
 * Created by CycloneBoy on 2017/8/25.
 */
public class BenzBuilder extends CarBuilder {
    private BenzModel benz = new BenzModel();

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.benz.setSequence(sequence);
    }

    @Override
    public CarModel getCarModel() {
        return this.benz;
    }
}
