package com.epam.lab.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Localization {

    private static Locale currentLocale;
    private static ResourceBundle messages;

    public final static String CLICK_BUTTON = "button_click";
    public final static String TEXT_CONTAINER_GET = "text_container_get";
    public final static String NO_TEXT_CONTAINER = "no_text_container";
    public final static String INPUT_SET_VALUE = "input_set_value";
    public final static String NO_INPUT = "no_input";

    private static void init() {
        currentLocale = new Locale("en");
        messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
    }

    public static String getMessage(String msg) {
        if (messages == null) {
            init();
        }
        return messages.getString(msg);
    }

    public static String getMessage(String msg, String... includes) {
        if (messages == null) {
            init();
        }

        return MessageFormat.format(messages.getString(msg), includes);
    }
}
