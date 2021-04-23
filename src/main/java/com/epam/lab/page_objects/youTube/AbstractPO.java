package com.epam.lab.page_objects.youTube;

import com.epam.lab.decorator.FieldDecorator;
import org.openqa.selenium.support.PageFactory;

import static com.epam.lab.singleton.DriverContainer.getDriver;

public class AbstractPO {
    protected AbstractPO() {
        PageFactory.initElements(new FieldDecorator(getDriver()), this);
    }
}
