package com.mahw.single;

public class Sample01 {

    private static Sample01 instance;

    private Sample01() {
    }

    public static Sample01 newInstance() {
        if(instance == null){
            instance =  new Sample01();
        }

        return instance;
    }
}
