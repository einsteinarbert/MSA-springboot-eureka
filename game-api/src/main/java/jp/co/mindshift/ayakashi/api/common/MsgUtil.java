package jp.co.mindshift.ayakashi.api.common;

import lombok.extern.slf4j.Slf4j;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Slf4j
public final class MsgUtil {
    private static final String BASE_NAME = "messages";
    public static final String SPLIT_CHAR = "###";

    public static String getMessage(String code, Locale locale) {
        return getMessage(code, null, locale);
    }

    public static String getMessage(String code, Object[] args, Locale locale, Object... ext) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_NAME, locale);
        String message;
        try {
            message = resourceBundle.getString(code);
        } catch (MissingResourceException ex) {
            log.debug(ex.getMessage(), ex);
            message = "-1";
        }
        if (ext.length > 0 && (Boolean) ext[0]) {
            return MessageFormat.format(message, args);
        }
        return MessageFormat.format(code + SPLIT_CHAR + message, args);
    }

    public static String getMessage(String code) {
        return getMessage(code, null, Locale.US);
    }

    public static String getMessageContent(String code) {
        return getMessage(code, null, Locale.US, true);
    }

    public static String getMessage(String code, Object... args) {
        return getMessage(code, args, Locale.US);
    }

    private MsgUtil() {
    }
}
