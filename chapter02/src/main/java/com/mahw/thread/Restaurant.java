package com.mahw.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Restaurant {

    private volatile AtomicInteger food;
    private Thread cook;
    private Thread waiter;

    public Restaurant() {
        this.food = new AtomicInteger(0);
        this.cook = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    System.out.println("出餐了~目前剩余" + food.incrementAndGet() + "份食物!");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.waiter = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    if(food.get() > 0){
                        System.out.println("食物分配成功~客户很满意~" + "目前剩余" + food.decrementAndGet() + "份食物!");
                    }else{
                        System.out.println("食物不够了！等待厨师烹饪...");
                    }
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    public void start(){
        System.out.println("餐厅开始营业...");
        this.cook.start();
        this.waiter.start();
    }

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();
        restaurant.start();
    }
}
