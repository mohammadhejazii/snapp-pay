package ir.snapppay.wallet.io.wallet.account;

import ir.snapppay.wallet.infrastructure.io.BaseBusinessException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */
public class AccountStateIsBlockedException extends BaseBusinessException implements Supplier<AccountStateIsBlockedException> {
    private String code;

    private AccountStateIsBlockedException(String code) {
        this.code = code;
    }

    public static AccountStateIsBlockedException instanceById(Long id) {
        AccountStateIsBlockedException exception = new AccountStateIsBlockedException("error.account.state.is-blocked.exception");
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
    public AccountStateIsBlockedException get() {
        return this;
    }

}
