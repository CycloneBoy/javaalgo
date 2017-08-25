package com.cycloneboy.dp.ch12.four;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by CycloneBoy on 2017/8/25.
 */
public class GamePlayH implements InvocationHandler {
    //被代理者
    private Class cls = null;
    //被代理的实例
    private Object obj = null;
    //我要代理谁
    public GamePlayH(Object obj){
        this.obj = obj;
    }

    //调用被代理的方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(this.obj,args);
        //如果是登录方法，则发送信息
        if(method.getName().equalsIgnoreCase("login")){
            System.out.println("有人在用我的账号登录");
        }
        return  result;
    }
}
