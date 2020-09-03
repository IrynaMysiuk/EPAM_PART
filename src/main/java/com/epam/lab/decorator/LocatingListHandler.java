package com.epam.lab.decorator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class LocatingListHandler implements InvocationHandler {

    private final ElementLocator locator;
    private Class<? extends AbstractPageElement> type;
    private Field field;

    public LocatingListHandler(Class<? extends AbstractPageElement> type, ElementLocator locator, Field field) {
        this.locator = locator;
        this.type = type;
        this.field = field;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object invoke(Object object, Method method, Object[] objects) throws Throwable {
        List<WebElement> elements = locator.findElements();

        List frameworkElements = new ArrayList();
        for (WebElement element : elements) {
            frameworkElements.add(createPageElement(element, type, field));
        }

        try {
            return method.invoke(frameworkElements, objects);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    public Object createPageElement(WebElement element, Class<? extends AbstractPageElement> type, Field field) {
        try {
            AbstractPageElement pageElement = type.getConstructor().newInstance();
            pageElement.fillFields(element, field);
            return pageElement;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
