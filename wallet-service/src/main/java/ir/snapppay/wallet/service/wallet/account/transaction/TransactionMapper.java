package ir.snapppay.wallet.service.wallet.account.transaction;

import ir.snapppay.wallet.data.wallet.account.Account;
import ir.snapppay.wallet.data.wallet.account.transaction.Transaction;
import ir.snapppay.wallet.infrastructure.data.Money;
import ir.snapppay.wallet.io.wallet.account.transaction.RegisterTransaction;
import ir.snapppay.wallet.io.wallet.account.transaction.TransactionResponse;
import ir.snapppay.wallet.io.wallet.account.transaction.TransactionState;
import ir.snapppay.wallet.service.wallet.account.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.12
 */

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionMapper {

    private final AccountMapper accountMapper;

    public Transaction initialize(final Account source, final Account destination, final RegisterTransaction request) {
        return Transaction.builder()
            .source(source)
            .destination(destination)
            .amount(Money.of(source.getBalance().getCurrency(),request.getAmount()))
            .type(request.getType())
            .state(TransactionState.REGISTERED)
            .build();
    }

    public TransactionResponse convert(final Transaction transaction) {
        return TransactionResponse.builder()
            .id(transaction.getId())
            .source(accountMapper.convert(transaction.getSource()))
            .destination(accountMapper.convert(transaction.getDestination()))
            .state(transaction.getState())
            .amount(transaction.getAmount())
            .type(transaction.getType())
            .build();
    }
}
