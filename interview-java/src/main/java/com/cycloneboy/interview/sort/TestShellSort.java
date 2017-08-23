package com.cycloneboy.interview.sort;

/**
 * Created by CycloneBoy on 2017/8/22.
 */
public class TestShellSort {
    public static int[] a = {4,2,1,6,3,6,0,-5,1,1};

    public static void main(String[] args) {
        int i;                       //循环计数变量
        int index = a.length ;      //数据索引变量

        System.out.println("排序前: ");
        for(int num : a){
            System.out.printf("%3s ",num);
        }
        System.out.println("");
        shellSort(index -1);//希尔排序

        //打印目前排序的结果
        System.out.print("排序中：");
        for(i = 0 ;i < index;i++){
            System.out.printf("%3s",a[i]);
        }
        System.out.println("");

    }

    public static void shellSort(int index){
        int i,j,k;      //循环计数变量
        int temp;       //暂存变量
        boolean change; //数据是否改变
        int dataLength; //分割集合的时间间隔长度
        int pointer;    //进行处理的位置

        dataLength = (int) index /2;    //初始集合间隔长度

        while (dataLength != 0){    // 数列仍可进行分割
            //对各个结合进行处理
            for(j=dataLength; j < index; j++){
                change = false;
                temp = a[j];    //暂存data[j]的值，待交换值时用
                pointer = j - dataLength;   //计算进行处理的位置

                //进行集合内数值的比较与交换值
                while(temp <a[pointer] && pointer >= 0 && pointer <= index){
                    a[pointer + dataLength] = a[pointer];
                    //计算下一个欲进行处理的位置
                    pointer = pointer - dataLength;
                    change = true;
                    if(pointer < 0 || pointer > index){
                        break;
                    }

                    //与最后的数值交换
                    a[pointer + dataLength] = temp;

                    if(change){
                        //打印目前排序的结果
                        System.out.print("排序中：");
                        for(k = 0 ;k < index;k++){
                            System.out.printf("%3s",a[k]);
                        }
                        System.out.println("");
                    }
                }
            }
            dataLength = dataLength/2;  //计算下次分割的间隔长度
        }
    }
}
