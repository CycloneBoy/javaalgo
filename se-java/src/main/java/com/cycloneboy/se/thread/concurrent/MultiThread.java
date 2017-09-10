package com.cycloneboy.se.thread.concurrent;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 使用JMX来查看一个普通的Java程序包含哪些线程
 * Created by CycloneBoy on 2017/9/10.
 */
public class MultiThread {

   public static void main(String[] args){
       ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
       ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
       for(ThreadInfo threadInfo : threadInfos){
           System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());

       }
   }

}
