package com.cycloneboy.dp.ch6;

/**
 * Created by CycloneBoy on 2017/8/17.
 */
public class NovelBook implements IBook{
    //书籍名称
    private String name;
    //书籍的价格
    private int price;
    //书籍的作者
    private String author;
    //通过构造函数传递书籍的数据

    public NovelBook(String name, int price, String author) {
        this.name = name;
        this.price = price;
        this.author = author;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getPrice() {
        return this.price;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }
}
