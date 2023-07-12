package ir.snapppay.wallet.service.wallet.account.transaction;

import ir.snapppay.wallet.data.wallet.Wallet;
import ir.snapppay.wallet.data.wallet.WalletRepository;
import ir.snapppay.wallet.data.wallet.account.Account;
import ir.snapppay.wallet.data.wallet.account.AccountRepository;
import ir.snapppay.wallet.data.wallet.account.transaction.Transaction;
import ir.snapppay.wallet.data.wallet.account.transaction.TransactionRepository;
import ir.snapppay.wallet.infrastructure.data.Money;
import ir.snapppay.wallet.io.wallet.WalletNotFoundException;
import ir.snapppay.wallet.io.wallet.WalletState;
import ir.snapppay.wallet.io.wallet.WalletStateIsBlockedException;
import ir.snapppay.wallet.io.wallet.account.AccountBalanceNotSufficientException;
import ir.snapppay.wallet.io.wallet.account.AccountNotFoundException;
import ir.snapppay.wallet.io.wallet.account.AccountState;
import ir.snapppay.wallet.io.wallet.account.AccountStateIsBlockedException;
import ir.snapppay.wallet.io.wallet.account.transaction.RegisterTransaction;
import ir.snapppay.wallet.io.wallet.account.transaction.TransactionResponse;
import ir.snapppay.wallet.io.wallet.account.transaction.TransactionState;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.12
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterTransactionService {

    private final WalletRepository walletRepository;
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionResponse register(final Long walletId, final Long accountId, final RegisterTransaction request) {
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(WalletNotFoundException.instanceById(walletId));
        Account source = accountRepository.findById(accountId).orElseThrow(AccountNotFoundException.instanceById(accountId));
        Account destination = accountRepository.findById(request.getDestinationAccount()).orElseThrow(AccountNotFoundException.instanceById(request.getDestinationAccount()));

        // verify source wallet
        verifyWalletNotBlocked(wallet);
        // verify destination wallet
        verifyWalletNotBlocked(destination.getWallet());

        // verify source account
        verifyAccountNotBlocked(source);
        // verify destination account
        verifyAccountNotBlocked(destination);

        // verify source account balance is sufficient
        verifySourceAccountBalanceIsSufficient(source, request.getAmount());

        Transaction transaction = transactionMapper.initialize(source, destination, request);
        transaction = transactionRepository.save(transaction);

        BigDecimal sourceBalance = source.getBalance().getAmount().subtract(request.getAmount());
        source.setBalance(Money.of(source.getBalance().getCurrency(), sourceBalance));

        BigDecimal destinationBalance = destination.getBalance().getAmount().add(request.getAmount());
        destination.setBalance(Money.of(destination.getBalance().getCurrency(), destinationBalance));

        accountRepository.save(source);
        accountRepository.save(destination);
        transaction.setState(TransactionState.SUCCEED);
        transactionRepository.save(transaction);

        return transactionMapper.convert(transaction);
    }

    private void verifySourceAccountBalanceIsSufficient(final Account account, final BigDecimal amount) {
        if (account.getBalance().getAmount().compareTo(amount) < 0) {
            throw AccountBalanceNotSufficientException.instanceById(account.getId());
        }
    }

    private void verifyAccountNotBlocked(final Account account) {
        if (account.getState().equals(AccountState.BLOCK)) {
            throw AccountStateIsBlockedException.instanceById(account.getId());
        }
    }

    private void verifyWalletNotBlocked(final Wallet wallet) {
        if (wallet.getState().equals(WalletState.BLOCK)) {
            throw WalletStateIsBlockedException.instanceById(wallet.getId());
        }
    }
}
