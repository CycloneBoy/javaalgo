package com.cycloneboy.se.algo.map.astart;

import javax.swing.*;
import java.awt.*;

/**
 * Created by CycloneBoy on 2017/10/13.
 */
public class AstarFrame extends JFrame {

    private Image image;
    //private Image backgroundImage;
    //private Image wallImage;
    private JPanel panel;
    private final static int DEFAULT_WIDTH=480;
    private final static int DEFAULT_HEIGHT=480;
    public AstarFrame()
    {

        this.init();
        this.add(panel);
    }
    public void init()
    {
        panel=new Main(image);
    }
    public static void main(String []args){
        AstarFrame frame=new AstarFrame();
        //frame.setIconImage(image);
        frame.setTitle("AStar FindingPath in JAVA");
        frame.setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT+20);
        frame.setVisible(true);
        frame.setResizable(false);
        //frame.add(new Main(image));
        //frame.setIconImage(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

}
