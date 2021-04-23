package com.epam.lab.annotation;

import com.epam.lab.business_objects.MainGmailBO;

import java.lang.reflect.Method;

public class ObjectUnknownType {
    private int age;
    private String name;
    // static Logger logger = LogManager.getLogger(Application.class.getName());
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainGmailBO.class);

    public String printName(String name) {
        return name;
    }

    public int printAge(int age) {
        return age;
    }

    public void setInfo(String name, int age) {
        this.age = age;
        this.name = name;
        log.info(String.format("Name : %s, Age: %d", this.name, this.age));
    }

    public void getUnknownType() {
        try {
            Class<?> cls = Class.forName("com.epam.lab.annotation.ObjectUnknownType");
            Object obj = cls.newInstance();
            Method method = cls.getDeclaredMethod("printName", String.class);
            log.info(method.invoke(obj, new String(Constants.myName)));
            method = cls.getDeclaredMethod("printAge", int.class);
            log.info(method.invoke(obj, Constants.myAge));
            method = cls.getDeclaredMethod("setInfo", String.class, int.class);
            method.invoke(obj, Constants.myName, Constants.myAge);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
