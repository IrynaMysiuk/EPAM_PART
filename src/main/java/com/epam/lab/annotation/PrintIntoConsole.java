package com.epam.lab.annotation;

import com.epam.lab.business_objects.MainGmailBO;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

public class PrintIntoConsole {
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface MyAnnotationField {
        int age() default Constants.myAge;

        String name() default "";

        String city() default "";
    }

    //static Logger logger = LogManager.getLogger(Application.class.getName());
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainGmailBO.class);

    @MyAnnotationField(age = Constants.myAge, name = Constants.myName, city = Constants.myCity)
    public String sayHello;

    public void outputTask3() throws SecurityException, NoSuchFieldException {
        PrintIntoConsole printIntoConsole = new PrintIntoConsole();
        Field field = printIntoConsole.getClass().getField("sayHello");
        MyAnnotationField annotation = field.getAnnotation(MyAnnotationField.class);
        log.info(String.format("I'm %s. I'm %d. I'm from %s", annotation.name(), annotation.age(), annotation.city()));
    }
}