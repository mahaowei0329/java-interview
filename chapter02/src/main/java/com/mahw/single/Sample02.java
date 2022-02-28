package com.mahw.single;

public class Sample02 {

    private static volatile Sample02 instance;

    private Sample02() {
    }

    public static Sample02 newInstance() {
        if(instance == null){
            synchronized (Sample02.class) {
                if (instance == null) {
                    instance = new Sample02();
                }
            }
        }

        return instance;
    }
}
