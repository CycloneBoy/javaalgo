package com.cycloneboy.interview.java;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

/** Created by CycloneBoy on 2017/8/1. */
public class Test {
  static int x, y;

  static {
    int x = 5;
  }

  public static void main(String[] args) {
    x--;
    System.out.println("main : " + x);
    myMethod();
    System.out.println(x + y++ + x);

    StringBuffer sb = new StringBuffer();
    StringBuilder sb2 = new StringBuilder();

    HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
    Hashtable<Object, Object> objectObjectHashtable = new Hashtable<>();

    List emptyList = Collections.EMPTY_LIST;
  }

  public static void myMethod() {
    y = x++ + ++x;
    System.out.println("myMethod: x = " + x + " y = " + y);
  }
}
