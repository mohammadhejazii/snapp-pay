package ir.snapppay.wallet.io.wallet.account;

import ir.snapppay.wallet.infrastructure.io.BaseBusinessException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.12
 */
public class AccountBalanceNotSufficientException extends BaseBusinessException implements Supplier<AccountBalanceNotSufficientException> {
    private String code;

    private AccountBalanceNotSufficientException(String code) {
        this.code = code;
    }

    public static AccountBalanceNotSufficientException instanceById(Long id) {
        AccountBalanceNotSufficientException exception = new AccountBalanceNotSufficientException("error.account.balance.not-sufficient.exception");
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
    public AccountBalanceNotSufficientException get() {
        return this;
    }

}
