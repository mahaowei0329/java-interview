package com.mahw.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Sample03 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class<Sample02> sample02Class = Sample02.class;
        Constructor<Sample02> declaredConstructor = sample02Class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Sample02 sample02 = declaredConstructor.newInstance();
        System.out.println(sample02);
    }
}
