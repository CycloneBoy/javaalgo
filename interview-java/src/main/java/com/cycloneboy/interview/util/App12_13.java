package com.cycloneboy.interview.util;

import javax.swing.*;
import java.awt.*;

/**
 * Created by CycloneBoy on 2017/8/15.
 */
public class App12_13 extends JFrame{

    static App12_13 frm=new App12_13();
    static JLabel jlab=new JLabel("标签：调整窗口的大小来理解GridBagLayout",JLabel.CENTER);
    static JTextArea jta1=new JTextArea("文本区1",5,15);
    static JTextArea jta2=new JTextArea("文本区2",5,15);
    static JTextField jtf=new JTextField("文本框");
    static JPanel jp=new JPanel();

    static JButton jbt1=new JButton("按钮1");
    static JButton jbt2=new JButton("按钮2");

    public static void main(String[] args){
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setTitle("网格包布局设置管理器GridBagLayout");
        frm.setSize(350,130);
        //frm.setLayout(new GridLayout());
        frm.setLayout(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.fill=GridBagConstraints.BOTH;
        gbc.anchor=GridBagConstraints.CENTER;
        Container conta=frm.getContentPane();

        frm.addCom(jlab,conta,gbc,0,0,1,4,0,0);
        frm.addCom(jta1,conta,gbc,1,0,2,1,5,1);
        frm.addCom(jta2,conta,gbc,1,3,1,1,5,1);
        frm.addCom(jtf,conta,gbc,2,3,1,1,5,0);
        frm.addCom(jbt1,conta,gbc,3,1,1,1,5,0);
        frm.addCom(jbt2,conta,gbc,3,2,1,1,5,0);

        jp.setBackground(Color.CYAN);
        jp.setBorder(new javax.swing.border.LineBorder(Color.RED));
        gbc.insets=new Insets(10, 10, 10, 10);
        frm.addCom(jp,conta,gbc,1,1,2,2,10,1);
        frm.setVisible(true);

    }
    private void addCom(Component c,
                        Container con,
                        GridBagConstraints gbcon,
                        int row, int col, int numberOfRow, int numberOfCol,
                        double weightX, double weightY){
        gbcon.gridx=col;
        gbcon.gridy=row;
        gbcon.gridwidth=numberOfCol;
        gbcon.gridheight=numberOfRow;
        gbcon.weightx=weightX;
        gbcon.weighty=weightY;
        con.add(c,gbcon);

    }
}
