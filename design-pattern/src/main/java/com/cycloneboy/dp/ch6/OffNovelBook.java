package com.cycloneboy.dp.ch6;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class OffNovelBook extends NovelBook {
    public OffNovelBook(String name, int price, String author) {
        super(name, price, author);
    }

    @Override
    public int getPrice() {
        //原价
        int selfPrice = super.getPrice();
        int offPrice = 0;
        if(selfPrice > 4000){ //原价大于40元，则打9折
            offPrice = selfPrice * 90/100;
        }else {
            offPrice = selfPrice * 80/100;
        }
        return offPrice;
    }
}
