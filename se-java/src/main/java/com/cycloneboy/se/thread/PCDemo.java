package com.cycloneboy.se.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用BlockingQueue完成生产者和消费者
 * Created by CycloneBoy on 2017/9/8.
 */
public class PCDemo {
    public static void main(String[] args) {
        final BlockingQueue<Character> bq ;
        bq = new ArrayBlockingQueue<Character>(26);
        final ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable producer = ()->{
            for(char ch ='A';ch <= 'Z';ch++){
                try{
                    bq.put(ch);
                    System.out.printf("%c produced by producer.%n",ch );
                }catch (InterruptedException ie){
                    System.out.println(ie.getMessage());
                    ie.printStackTrace();
                }
            }
        };
        executor.execute(producer);

        Runnable consumber = ()->{
            char ch = '\0';
            do{
                try {
                    ch = bq.take();
                    System.out.printf("%s consumed by consumer.%n", ch);
                }catch (InterruptedException ie){
                    System.out.println(ie.getMessage());
                    ie.printStackTrace();
                }
            }while (ch != 'Z');
            executor.shutdownNow();
        };
        executor.execute(consumber);
    }
}
