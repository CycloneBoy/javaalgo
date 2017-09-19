package com.cycloneboy.se.io;

import java.io.*;

/**
 * Created by CycloneBoy on 2017/9/19.
 */
public class ObjectIO {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("文件"));

        Object o = null;

        o = new ObjectDemo();
        oos.writeObject(o);
        oos.flush();
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("文件"));
        ObjectDemo od = (ObjectDemo)(ois.readObject());
        if(od instanceof ObjectDemo){
            System.out.println(od.toString());
        }

        ois.close();
    }
}

class ObjectDemo implements Serializable{

    @Override
    public String toString() {
        return "A string of ObjectDemo".toString();
    }
}
