package com.epam.lab.annotation;


import com.epam.lab.business_objects.MainGmailBO;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokeAnnotation {
   // static Logger logger = LogManager.getLogger(Demo.class.getName());
   private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainGmailBO.class);
    @Demo.MyAnnotation(value = 10)
    public void sayHello() {
        log.info("hello annotation");
    }

    public void myMethod(String... strings) {
        for (String str : strings) {
            log.info(str);
        }
    }

    public void myMethod(String a, int... args) {
        for (int arg : args) {
            log.info(String.format("%s: %d", a, arg));
        }
    }

    public void getInvoke() {
        Class<?> cls;
        try {
            cls = Class.forName("com.epam.lab.annotation.InvokeAnnotation");
            Method method = cls.getMethod("myMethod", String[].class);
            InvokeAnnotation ownAnnotation = new InvokeAnnotation();
            method.invoke(ownAnnotation, new Object[]{Constants.argStr});
            method = cls.getMethod("myMethod", String.class, int[].class);
            method.invoke(ownAnnotation, Constants.myName, Constants.argInt);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException
                | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

