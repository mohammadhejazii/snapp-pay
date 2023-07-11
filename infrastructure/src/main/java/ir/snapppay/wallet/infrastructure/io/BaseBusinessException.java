package ir.snapppay.wallet.infrastructure.io;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */
public abstract class BaseBusinessException extends RuntimeException {

    private Map<String, Object> messageArgs;

    public void addMessageArg(final String messageArg, final Object messageVal) {
        if (messageArgs == null) {
            this.messageArgs = new HashMap<>();
        }
        this.messageArgs.put(messageArg, messageVal);
    }

    public Map<String, Object> getMessageArgs() {
        return messageArgs;
    }


    public abstract String getMessageKey();


    public String getErrorCode() {
        return "-";
    }


    public int getHttpStatus() {
        return 500;
    }
}
