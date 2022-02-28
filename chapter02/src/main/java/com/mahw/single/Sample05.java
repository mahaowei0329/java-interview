package com.mahw.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Sample05 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        Class<Sample04> sample04Class = Sample04.class;
        Constructor<Sample04> declaredConstructor = sample04Class.getDeclaredConstructor();
        declaredConstructor.setAccessible(true);
        Sample04 sample04 = declaredConstructor.newInstance();
        System.out.println(sample04);
    }

}
