package com.epam.lab.annotation;


import com.epam.lab.business_objects.MainGmailBO;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class UnknownType {
    public static int a;
    public static int b;
    //static Logger logger = LogManager.getLogger(Application.class.getName());
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainGmailBO.class);

    UnknownType() {
        UnknownType.a = 1;
        UnknownType.b = 1;
    }

    private void printInfo(Field field) throws IllegalArgumentException, IllegalAccessException {
        log.info("name = " + field.getName());
        log.info("decl class = " + field.getDeclaringClass());
        log.info("type = " + field.getType());
        log.info("modifiers = " + Modifier.toString(field.getModifiers()));
    }

    public void getUnknownType() {
        Class<?> cls;
        try {
            cls = Class.forName("com.epam.lab.annotation.UnknownType");
            Field fieldlist[] = cls.getDeclaredFields();
            for (int i = 0; i < fieldlist.length; i++) {
                if (fieldlist[i].getType().equals(int.class)) {
                    fieldlist[i].set(cls, Constants.myAge + i);
                }
                printInfo(fieldlist[i]);
                log.info("get: " + fieldlist[i].get(cls));
            }
        } catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}