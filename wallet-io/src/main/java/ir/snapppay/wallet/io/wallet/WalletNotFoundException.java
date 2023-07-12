package ir.snapppay.wallet.io.wallet;

import ir.snapppay.wallet.infrastructure.io.BaseBusinessException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */
public class WalletNotFoundException extends BaseBusinessException implements Supplier<WalletNotFoundException> {
    private String code;

    private WalletNotFoundException(String code) {
        this.code = code;
    }

    public static WalletNotFoundException instanceById(Long id) {
        WalletNotFoundException exception = new WalletNotFoundException("error.wallet.id.not-found.exception");
        exception.addMessageArg("id", id);
        return exception;
    }

    public static WalletNotFoundException instanceByUserId(final Long userId) {
        WalletNotFoundException exception = new WalletNotFoundException("error.wallet.user.id.not-found.exception");
        exception.addMessageArg("userId", userId);
        return exception;
    }

    @Override
    public int getHttpStatus() {
        return HttpStatus.NOT_FOUND.value();
    }

    @Override
    public String getMessageKey() {
        return code;
    }

    @Override
    public WalletNotFoundException get() {
        return this;
    }

}
