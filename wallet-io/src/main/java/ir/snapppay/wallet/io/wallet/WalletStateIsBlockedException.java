package ir.snapppay.wallet.io.wallet;

import ir.snapppay.wallet.infrastructure.io.BaseBusinessException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */
public class WalletStateIsBlockedException extends BaseBusinessException implements Supplier<WalletStateIsBlockedException> {
    private String code;

    private WalletStateIsBlockedException(String code) {
        this.code = code;
    }

    public static WalletStateIsBlockedException instanceById(Long id) {
        WalletStateIsBlockedException exception = new WalletStateIsBlockedException("error.wallet.state.is-blocked.exception");
        exception.addMessageArg("id", id);
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
    public WalletStateIsBlockedException get() {
        return this;
    }

}
