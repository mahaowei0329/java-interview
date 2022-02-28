package com.mahw.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

public class ThreadLocalTest {

    private static ThreadLocal<String> threadLocal;
    private static ConcurrentHashMap<Thread, String> threadLocal1 = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal1.put(Thread.currentThread(), "A");
                System.out.println("print A to ThreadLocal Success");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadLocal1.get(thread1));
            }
        });
        thread1.start();
        thread2.start();
    }
}
