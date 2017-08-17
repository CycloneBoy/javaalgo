package com.cycloneboy.dp.ch4;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public abstract class AbstractSearcher {
    protected PettyGirl pettyGirl;

    public AbstractSearcher(PettyGirl _pettyGirl){
        this.pettyGirl = _pettyGirl;
    }

    //搜索美女，列出美女信息
    public abstract void show();
}
