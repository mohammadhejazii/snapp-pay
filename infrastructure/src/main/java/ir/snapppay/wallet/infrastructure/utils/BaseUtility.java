package ir.snapppay.wallet.infrastructure.utils;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.11
 */
public final class BaseUtility {


    public static String getPersianMessage(
        final MessageSource messageSource,
        final String code,
        final Object[] args
    ) {
        return getMessage(messageSource, code, new Locale("fa", "IR"), args);
    }

    public static String getEnglishMessage(
        final MessageSource messageSource,
        final String code,
        final Object[] args
    ) {
        return getMessage(messageSource, code, Locale.ENGLISH, args);
    }


    public static String getMessage(
        final MessageSource messageSource,
        final String code,
        final Locale locale,
        final Object[] args
    ) {
        if (code == null || code.isEmpty()) {
            return String.format("Exception code has not present");
        }
        try {
            return messageSource.getMessage(
                code,
                args,
                String.format("Message with key [%s] NOT found", code),
                locale
            );
        } catch (Exception e) {
            return String.format("Message with key [%s] NOT found", code);
        }
    }

}
