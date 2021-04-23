package com.epam.lab.business_objects.youTubeBO;

import com.epam.lab.business_objects.MainGmailBO;
import com.epam.lab.page_objects.youTube.YouTubePO;
import org.openqa.selenium.Keys;

public class YouTubeBO {
    private static final String YOUTUBE ="YouTube" ;
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainGmailBO.class);

    public void openYouTubePage() {
        YouTubePO youTubePO = new YouTubePO();
        log.info("Check loading Google page");
        youTubePO.getSearchField().sendText(YOUTUBE);
        youTubePO.getSearchField().sendText(Keys.ENTER);
        youTubePO.getYouTubeItem().click();
        youTubePO.getLibraryItem().click();
    }
}
