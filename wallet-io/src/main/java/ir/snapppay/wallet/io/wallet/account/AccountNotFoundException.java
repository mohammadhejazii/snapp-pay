package ir.snapppay.wallet.io.wallet.account;

import ir.snapppay.wallet.infrastructure.io.BaseBusinessException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */
public class AccountNotFoundException extends BaseBusinessException implements Supplier<AccountNotFoundException> {
    private String code;

    private AccountNotFoundException(String code) {
        this.code = code;
    }

    public static AccountNotFoundException instanceById(Long id) {
        AccountNotFoundException exception = new AccountNotFoundException("error.account.id.not-found.exception");
        exception.addMessageArg("id", id);
        return exception;
    }

    public static AccountNotFoundException instanceByWalletId(final Long walletId) {
        AccountNotFoundException exception = new AccountNotFoundException("error.account.wallet-id.not-found.exception");
        exception.addMessageArg("walletId", walletId);
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
    public AccountNotFoundException get() {
        return this;
    }

}
