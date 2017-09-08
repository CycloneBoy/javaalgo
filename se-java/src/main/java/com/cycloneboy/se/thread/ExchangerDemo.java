package com.cycloneboy.se.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 交换器
 * Created by CycloneBoy on 2017/9/7.
 */
public class ExchangerDemo {
    final static Exchanger<Databuffer> exchanger = new Exchanger<>();

    final static Databuffer initialEmptyBuffer = new Databuffer();
    final static Databuffer initialFullBuffer = new Databuffer("I");

    public static void main(String[] args) {

        class FillingLoop implements Runnable{
            int count = 0;

            @Override
            public void run() {
                Databuffer currentBuffer = initialEmptyBuffer;
                try {
                    while (true){
                        addToBuffer(currentBuffer);
                        if(currentBuffer.isFull()){
                            System.out.println("filling thread wants to exchange");
                            currentBuffer = exchanger.exchange(currentBuffer);
                            System.out.println("filling thread receices exchange");
                        }
                    }
                }catch (InterruptedException ie){
                    System.out.println("filling thread interrupted");
                }
            }

            void addToBuffer(Databuffer buffer) {
                String item = "NI" + count++;
                System.out.println("Adding: " + item);
                buffer.add(item);
            }
        }

        class EmptyingLoop implements Runnable{
            @Override
            public void run() {
                Databuffer currentBuffer = initialFullBuffer;
                try{
                    while (true){
                        takeFromBuffer(currentBuffer);
                        if(currentBuffer.isEmpty()){
                            System.out.println("emptying thread wants to exchange" );
                            currentBuffer = exchanger.exchange(currentBuffer);
                            System.out.println("emptying thread receives exchange");
                        }
                    }
                }catch (InterruptedException ie){
                    System.out.println("emptying thread interrupted");
                }
            }

            void takeFromBuffer(Databuffer buffer){
                System.out.println("taking:" + buffer.remove());
            }
        }

        new Thread(new EmptyingLoop()).start();
        new Thread(new FillingLoop()).start();
    }
}

class Databuffer{
    private final static int MAXITEMS = 10;

    private final List<String> items = new ArrayList<>();

    Databuffer(){}

    Databuffer(String prefix){
        for(int i = 0; i < MAXITEMS ; i++){
            String item = prefix + i;
            System.out.printf("Adding %s%n",item);
            items.add(item);
        }
    }

    synchronized void add(String s){
        if(!isFull()){
            items.add(s);
        }
    }

    synchronized boolean isEmpty(){
        return items.size() == 0;
    }

    synchronized   boolean isFull() {
        return items.size() == MAXITEMS;
    }

    synchronized String remove(){
        if(!isEmpty()){
            return items.remove(0);
        }
        return  null;
    }

}
