package com.epam.lab.decorator;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.lang.reflect.Field;

import static com.epam.lab.singleton.DriverManager.getWait;

public class AbstractPageElement {
    protected WebElement wrappedElement;
    protected Field field;
    protected String name;
    protected By by;
    protected static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(AbstractPageElement.class);

    public void fillFields(WebElement wrappedElement, Field field) {

        this.wrappedElement = wrappedElement;
        this.field = field;
        this.by = getBy(field);
        this.name = getName(field);
    }

    private String getName(Field field) {
        return field.isAnnotationPresent(Description.class) ? field
                .getAnnotation(Description.class).value() : field.getName();
    }

    private By getBy(Field field) {
        if (field.isAnnotationPresent(FindBy.class)) {
            FindBy findBy = field.getAnnotation(FindBy.class);
            return transformFindByToBy(findBy);
        }
        return null;
    }

    private By transformFindByToBy(FindBy findBy) {
        if (!findBy.name().isEmpty())
            return By.name(findBy.name());

        if (!findBy.className().isEmpty())
            return By.className(findBy.className());

        if (!findBy.css().isEmpty())
            return By.cssSelector(findBy.css());

        if (!findBy.id().isEmpty())
            return By.id(findBy.id());

        if (!findBy.linkText().isEmpty())
            return By.linkText(findBy.linkText());

        if (!findBy.tagName().isEmpty())
            return By.tagName(findBy.tagName());

        if (!findBy.tagName().isEmpty())
            return By.tagName(findBy.tagName());

        if (!findBy.partialLinkText().isEmpty())
            return By.partialLinkText(findBy.partialLinkText());

        if (!findBy.xpath().isEmpty())
            return By.xpath(findBy.xpath());

        if (!findBy.using().isEmpty())
            return transformFindByToByUsingHow(findBy);

        return null;
    }

    private By transformFindByToByUsingHow(FindBy findBy) {
        String using = findBy.using();
        How how = findBy.how();

        if (how.equals(How.ID_OR_NAME))
            return By.xpath("//*[@name='" + using + "']|//*[@id='" + using + "']");

        if (how.equals(How.CLASS_NAME))
            return By.className(using);

        if (how.equals(How.CSS))
            return By.cssSelector(using);

        if (how.equals(How.ID))
            return By.id(using);

        if (how.equals(How.LINK_TEXT))
            return By.linkText(using);

        if (how.equals(How.NAME))
            return By.name(using);

        if (how.equals(How.PARTIAL_LINK_TEXT))
            return By.partialLinkText(using);

        if (how.equals(How.TAG_NAME))
            return By.tagName(using);

        if (how.equals(How.XPATH))
            return By.xpath(using);

        return null;
    }

    public WebElement getElement() {
        return getWebElementWithWait(WaitCondition.VISIBILITY, wrappedElement);
    }

    protected WebElement getWebElementWithWait(WaitCondition waitCondition, WebElement element) {
        if (waitCondition.equals(WaitCondition.VISIBILITY))
            return getWait().until(ExpectedConditions.visibilityOf(element));
        else if (waitCondition.equals(WaitCondition.CLICKABLE))
            return getWait().until(ExpectedConditions.elementToBeClickable(element));
        return getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public enum WaitCondition {
        VISIBILITY,
        CLICKABLE
    }
}
