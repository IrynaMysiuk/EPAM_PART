package com.epam.lab.decorator;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.util.List;

public class FieldDecorator extends DefaultFieldDecorator {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AbstractPageElement.class);

    public FieldDecorator(final SearchContext searchContext) {
        super(new DefaultElementLocatorFactory(searchContext));
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        ElementLocator locator = factory.createLocator(field);
        if (locator == null) {
            return null;
        }
        if (AbstractPageElement.class.isAssignableFrom(field.getType())) {
            try {
                WebElement proxy = proxyForLocator(loader, locator);
                return createPageElement(proxy, field);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("WebElement can't be represented as "
                        + field.getType());
                return null;
            }
        } else if (List.class.isAssignableFrom(field.getType())) {
            ParameterizedType listType = (ParameterizedType) field
                    .getGenericType();
            return proxyForListLocator(
                    (Class<? extends AbstractPageElement>) listType.getActualTypeArguments()[0],
                    loader, locator, field);
        } else
            return super.decorate(loader, field);
    }

    protected List<?> proxyForListLocator(Class<? extends AbstractPageElement> type,
                                          ClassLoader loader, ElementLocator locator,
                                          Field field) {
        InvocationHandler handler = new LocatingListHandler(type, locator, field);
        List<? extends AbstractPageElement> proxy;
        proxy = (List<? extends AbstractPageElement>) Proxy.newProxyInstance(
                loader, new Class[]{List.class}, handler);
        return proxy;
    }

    public Object createPageElement(WebElement element, Field field) {
        try {
            AbstractPageElement pageElement = (AbstractPageElement) field.getType().getConstructor().newInstance();
            pageElement.fillFields(element, field);
            return pageElement;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
