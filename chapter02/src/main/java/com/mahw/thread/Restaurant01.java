package com.mahw.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Restaurant01 {

    private volatile AtomicInteger food;
    private Thread cook;
    private Thread waiter;

    public Restaurant01() {
        this.food = new AtomicInteger();
        this.cook = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (food) {
                        try {
                            //出餐慢一点让消费者等待
                            TimeUnit.SECONDS.sleep(3);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("出餐了~目前剩余" + food.incrementAndGet() + "份食物!");
                        food.notify();
                    }
                }
            }
        });
        this.waiter = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (food) {
                        if (food.get() > 0) {
                            System.out.println("食物分配成功~客户很满意~" + "目前剩余" + food.decrementAndGet() + "份食物!");
                        } else {
                            System.out.println("食物不够了！等待厨师烹饪...");
                            try {
                                food.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        });
    }

    public void start() {
        System.out.println("餐厅开始营业...");
        this.cook.start();
        this.waiter.start();
    }

    public static void main(String[] args) {
        Restaurant01 restaurant = new Restaurant01();
        restaurant.start();
    }
}
