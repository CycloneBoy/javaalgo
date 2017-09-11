package com.cycloneboy.se.thread.concurrent;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * ConnectionPool 模拟数据库连接池
 * Created by CycloneBoy on 2017/9/11.
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<Connection>();

    public ConnectionPool(int initialSize){
        if(initialSize > 0){
            for(int i = 0; i< initialSize;i++){
                pool.addLast(ConnectionDriver.createConnection());
                System.out.println("初始化连接池: " + pool.getLast() + " 连接池数量: " + pool.size());
            }
        }
    }

    public void releaseConnection(Connection connection){
        if(connection != null){
            synchronized (pool){
                pool.addLast(connection);
                System.out.println("释放一个连接");
                pool.notifyAll();
            }
        }
    }

    //在mills 内无法获取到连接，将返回null
    public Connection fetchConnection(long mills) throws InterruptedException{
        synchronized (pool){
            if(mills <=0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while(pool.isEmpty() && remaining > 0){
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if(!pool.isEmpty()){
                    result = pool.removeFirst();
                }
                System.out.println("获取一个连接:" + result + " 连接池数量: " + pool.size() );
                return result;
            }
        }
    }
}
