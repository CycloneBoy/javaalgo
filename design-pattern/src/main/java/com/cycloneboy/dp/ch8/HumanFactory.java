package com.cycloneboy.dp.ch8;

/**
 * Created by CycloneBoy on 2017/8/18.
 */
public class HumanFactory extends AbstractHumanFactory{
    @Override
    public <T extends Human> T createHuman(Class<T> c) {
        //定义一个生产的人种
        Human human = null;
        try {
           //生产一个人种
            human = (T)Class.forName(c.getName()).newInstance();
        } catch (Exception e) {
            System.out.println("人种产生错误!");
        }
        return (T)human;
    }
}
