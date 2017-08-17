package com.cycloneboy.dp.ch3;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class Driver implements IDriver {
    @Override
    public void driver(ICar car) {
        car.run();
    }
}
