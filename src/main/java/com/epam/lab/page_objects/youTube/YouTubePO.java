package com.epam.lab.page_objects.youTube;

import com.epam.lab.decorator.NavigationLink;
import com.epam.lab.decorator.TextField;
import org.openqa.selenium.support.FindBy;

public class YouTubePO extends AbstractPO {
    @FindBy(name = "q")
    private TextField searchType;
    @FindBy(xpath = "//h2[contains(text(),'Результати веб-пошуку')]/../link/../div/div/div/a")
    private NavigationLink youTubeItem;
    @FindBy(xpath = "//ytd-guide-entry-renderer/a[@title='Бібліотека']")
    private NavigationLink libraryItem;

    public TextField getSearchField() {
        return searchType;
    }
    public NavigationLink getYouTubeItem() {
        return youTubeItem;
    }
    public NavigationLink getLibraryItem() {
        return libraryItem;
    }
}
