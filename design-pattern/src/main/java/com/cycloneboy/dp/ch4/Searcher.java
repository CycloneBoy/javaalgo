package com.cycloneboy.dp.ch4;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class Searcher extends AbstractSearcher{

    public Searcher(PettyGirl _pettyGirl) {
        super(_pettyGirl);
    }

    @Override
    public void show() {
        System.out.println("------------美女的信息如下:------------");
        //展示面容
        super.pettyGirl.goodLooking();
        //展示身材
        super.pettyGirl.niceFigure();
        //展示气质
        super.pettyGirl.greateTemperament();
    }
}
