package com.epam.lab.annotation;

import com.epam.lab.business_objects.MainGmailBO;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Demo {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainGmailBO.class);

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    @interface MyAnnotation {
        int value();
    }

    public static void main(String[] args) throws SecurityException, NoSuchFieldException, ClassNotFoundException {
        log.info("--------Print into console----------");
        PrintIntoConsole printIntoConsole = new PrintIntoConsole();
        printIntoConsole.outputTask3();
        log.info("-------- Invoke method----------");
        InvokeAnnotation invokeAnnotation = new InvokeAnnotation();
        invokeAnnotation.getInvoke();
        log.info("--------Print unknown type----------");
        UnknownType unknownType = new UnknownType();
        unknownType.getUnknownType();
        log.info("--------Print unknown type and show all information----------");
        ObjectUnknownType objectUnknownType = new ObjectUnknownType();
        objectUnknownType.getUnknownType();
        log.info("--------Own Annotation----------");
        Class<?> cls = Class.forName("com.epam.lab.annotation.OwnAnnotation");
        OwnAnnotation ownAnnotation = new OwnAnnotation(cls);
        ownAnnotation.getClassName();
        ownAnnotation.allInfoMethods();
        ownAnnotation.allInfoConstructors();
        ownAnnotation.allInfoFields();
    }
}