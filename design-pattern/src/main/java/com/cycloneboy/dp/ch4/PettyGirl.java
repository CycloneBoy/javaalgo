package com.cycloneboy.dp.ch4;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class PettyGirl  implements IGoodBodyGirl,ICreateTemperamentGirl{
    private String name;

    public PettyGirl(String _name){
        this.name = _name;
    }

    @Override
    public void goodLooking() {
        System.out.println(this.name + "---脸蛋很漂亮!");
    }

    @Override
    public void niceFigure() {
        System.out.println(this.name + "---身材非常棒!");
    }

    @Override
    public void greateTemperament() {
        System.out.println(this.name + "---气质非常好!");
    }
}
