package com.epam.lab.annotation;

import com.epam.lab.business_objects.MainGmailBO;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class OwnAnnotation {
    public Class<?> cls;
    //static Logger logger = LogManager.getLogger(Application.class.getName());
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainGmailBO.class);

    OwnAnnotation(Class<?> className) {
        this.cls = className;
    }

    private void getMethodParameters(Method method) {
        Class<?> pvec[] = method.getParameterTypes();
        for (int j = 0; j < pvec.length; j++)
            log.info(" param #" + j + " " + pvec[j]);
        Class<?> evec[] = method.getExceptionTypes();
        for (int j = 0; j < evec.length; j++)
            log.info("exc #" + j + " " + evec[j]);
    }

    private void getConstuctorParamreters(Constructor<?> ct) {
        Class<?> pvec[] = ct.getParameterTypes();
        for (int j = 0; j < pvec.length; j++)
            log.info("param #" + j + " " + pvec[j]);
        Class<?> evec[] = ct.getExceptionTypes();
        for (int j = 0; j < evec.length; j++)
            log.info("exc #" + j + " " + evec[j]);
    }

    public void allInfoMethods() {
        try {
            Method methList[] = cls.getDeclaredMethods();
            for (Method method : methList) {
                log.info(
                        String.format("name  = %s;\ndecl class = %s", method.getName(), method.getDeclaringClass()));
                getMethodParameters(method);
                log.info(String.format("return type = %s\n-----", method.getReturnType()));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }


    public void allInfoConstructors() {
        try {
            Constructor<?> ctorList[] = cls.getDeclaredConstructors();
            for (Constructor<?> ct : ctorList) {
                System.out
                        .println(String.format("name  = %s;\ndecl class = %s;", ct.getName(), ct.getDeclaringClass()));
                getConstuctorParamreters(ct);
                log.info("-----");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void allInfoFields() {
        try {
            Field fieldList[] = cls.getDeclaredFields();
            for (Field field : fieldList) {
                log.info("name   = " + field.getName());
                log.info("decl class = " + field.getDeclaringClass());
                log.info("type   = " + field.getType());
                log.info("modifiers = " + Modifier.toString(field.getModifiers()));
                log.info("-----");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public void getClassName() {
        log.info(String.format("Class name: %s\n", cls.getName()));
    }
}

