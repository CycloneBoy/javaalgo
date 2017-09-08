package com.cycloneboy.se.thread;

import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * 借助Fork/Join框架将两个矩阵的实例相乘
 * Created by CycloneBoy on 2017/9/8.
 */
public class MatMultDemo extends RecursiveAction {
    private final Matrix a,b,c;
    private final int row;

    public MatMultDemo(Matrix a,Matrix b,Matrix c){
        this(a,b,c,-1);
    }

    public MatMultDemo(Matrix a,Matrix b,Matrix c, int row){
        if(a.getCols() != b.getRows()){
            throw  new IllegalArgumentException("row/columns mismatch");
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.row =row;
    }


    @Override
    protected void compute() {
        if(row == 1){
            List<MatMultDemo> tasks = new ArrayList<>();
            for(int row = 0;row < a.getRows();row++){
                tasks.add(new MatMultDemo(a,b,c,row));
            }
            invokeAll(tasks);
        }else {
            multiplyRowByColumn(a,b,c,row);
        }

    }

    private static void multiplyRowByColumn(Matrix a,
                                            Matrix b, Matrix c, int row) {
        for(int j = 0; j< b.getCols();j++){
            for(int k = 0; k < a.getCols();k++){
                c.setValue(row,j,c.getValue(row,j) + a.getValue(row,k) * b.getValue(k,j));;
            }
        }
    }
}
