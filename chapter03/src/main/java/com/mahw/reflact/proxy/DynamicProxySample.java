package com.mahw.reflact.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxySample {

    public static void main(String[] args) {
        //一个被代理的实现类
        BusStation busStation = new BusStation();
        Station stationProxy = (Station) MyInvocationHandler.newProxy(busStation);
        stationProxy.saleTicket();
    }
}

class MyInvocationHandler implements InvocationHandler{
    private Object field;

    private MyInvocationHandler() {
    }

    @Override
    /**
     * proxy 代理对象
     * method 被代理方法
     * args 传参
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        method.invoke(this.field, args);
        System.out.println("Do Nothing");
        return null;
    }

    public static Object newProxy(Object obj){
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
        myInvocationHandler.field = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), myInvocationHandler);
    }
}
