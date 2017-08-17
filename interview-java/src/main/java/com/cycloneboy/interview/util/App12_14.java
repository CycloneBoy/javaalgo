package com.cycloneboy.interview.util;

import javax.swing.*;
import java.awt.*;

/**
 * Created by CycloneBoy on 2017/8/15.
 */
public class App12_14 {

    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        JFrame frm=new JFrame("盒式布局管理器");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setSize(220, 130);
        Container app=frm.getContentPane();
        BoxLayout bl=new BoxLayout(app, BoxLayout.X_AXIS);
        frm.setLayout(bl);
        JButton but;
        for(int i=1;i<4;i++){
            but=new JButton("按钮"+i);
            frm.add(but);
        }
        frm.setVisible(true);

    }
}
