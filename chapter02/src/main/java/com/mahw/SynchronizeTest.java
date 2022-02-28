package com.mahw;

public class SynchronizeTest {

    public static void main(String[] args) {

    }

    public synchronized static void test(){
        synchronized (SynchronizeTest.class){

        }
    }
}
