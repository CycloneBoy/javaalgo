package com.cycloneboy.interview.java;

/**
 * Created by CycloneBoy on 2017/8/12.
 *    1.首先，需要明白类的加载顺序。
     (1) 父类静态对象和静态代码块
     (2) 子类静态对象和静态代码块
     (3) 父类非静态对象和非静态代码块
     (4) 父类构造函数
     (5) 子类 非静态对象和非静态代码块
     (6) 子类构造函数
     其中：类中静态块按照声明顺序执行，并且(1)和(2)不需要调用new类实例的时候就执行了(意思就是在类加载到方法区的时候执行的)
 */
public class Test8_12_1 {

        public static Test8_12_1 t1 = new Test8_12_1();
        {
            System.out.println("blockA");
        }
        static
        {
            System.out.println("blockB");
        }

        public static void main(String[] args)
        {

            long test=012;
            float f=-412;
            //int other =(int)true;
            double d=0x12345678;
            //byte b=128;

            Test8_12_1 t2 = new Test8_12_1();
        }

}
