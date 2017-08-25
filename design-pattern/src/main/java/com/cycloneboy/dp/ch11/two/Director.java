package com.cycloneboy.dp.ch11.two;

import java.util.ArrayList;

/**
 * Created by CycloneBoy on 2017/8/25.
 */
public class Director {
    private ArrayList<String> sequence = new ArrayList<>();
    private BenzBuilder benzBuilder = new BenzBuilder();
    private BMWBuilder bmwBuilder = new BMWBuilder();

    /**
     * A类型的奔驰模型，先start,然后stop，其他什么引擎、喇叭一概没有
     * @return
     */
    public BenzModel getABenzModel(){
        //清理场景
        this.sequence.clear();
        //ABenzModel的执行顺序
        this.sequence.add("start");
        this.sequence.add("stop");
        this.benzBuilder.setSequence(sequence);
        return (BenzModel)this.benzBuilder.getCarModel();
    }

    /**
     * B类型的奔驰模型，先发动引擎、然后start,然后stop，没有喇叭
     * @return
     */
    public BenzModel getBBenzModel(){
        //清理场景
        this.sequence.clear();
        //BBenzModel的执行顺序
        this.sequence.add("engine boom");
        this.sequence.add("start");
        this.sequence.add("stop");
        this.benzBuilder.setSequence(sequence);
        return (BenzModel)this.benzBuilder.getCarModel();
    }

    /**
     * C类型的宝马模型，先按下喇叭（炫耀一下)、然后start,然后stop
     * @return
     */
    public BMWModel getCBMWModel(){
        this.sequence.clear();
        this.sequence.add("alarm");
        this.sequence.add("start");
        this.sequence.add("stop");
        this.bmwBuilder.setSequence(sequence);
        return (BMWModel)this.bmwBuilder.getCarModel();
    }

    /**
     * D类型的宝马模型，只有一个功能，就是跑，启动起来就是跑，永远不停止
     * @return
     */
    public BMWModel getDBMWModel(){
        this.sequence.clear();
        this.sequence.add("start");
        this.bmwBuilder.setSequence(sequence);
        return (BMWModel)this.bmwBuilder.getCarModel();
    }
}
