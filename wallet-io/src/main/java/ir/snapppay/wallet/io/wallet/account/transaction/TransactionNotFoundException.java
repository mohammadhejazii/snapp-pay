package ir.snapppay.wallet.io.wallet.account.transaction;

import ir.snapppay.wallet.infrastructure.io.BaseBusinessException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */
public class TransactionNotFoundException extends BaseBusinessException implements Supplier<TransactionNotFoundException> {
    private String code;

    private TransactionNotFoundException(String code) {
        this.code = code;
    }

    public static TransactionNotFoundException instanceById(Long id) {
        TransactionNotFoundException exception = new TransactionNotFoundException("error.transaction.id.not-found.exception");
        exception.addMessageArg("id", id);
        return exception;
    }

    public static TransactionNotFoundException instanceByWalletId(final Long walletId) {
        TransactionNotFoundException exception = new TransactionNotFoundException("error.account.wallet-id.not-found.exception");
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
    public TransactionNotFoundException get() {
        return this;
    }

}
