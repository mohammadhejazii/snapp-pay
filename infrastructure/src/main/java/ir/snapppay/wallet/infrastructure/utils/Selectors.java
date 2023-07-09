package ir.snapppay.wallet.infrastructure.utils;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.09
 */
public class Selectors {


    public static String selectNonNull(final String originalValue) {
        String result = originalValue;
        if (result == null) {
            result = "";
        }
        return result;
    }

    public static <T> T getOrDefault(final T originalValue, final T defaultValue) {
        T result = originalValue;
        if (result == null) {
            result = defaultValue;
        }
        return result;
    }


}
