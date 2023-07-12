package ir.snapppay.wallet.service.wallet.account.transaction;

import ir.snapppay.wallet.data.wallet.account.transaction.Transaction;
import ir.snapppay.wallet.data.wallet.account.transaction.TransactionRepository;
import ir.snapppay.wallet.io.wallet.account.transaction.TransactionNotFoundException;
import ir.snapppay.wallet.io.wallet.account.transaction.TransactionResponse;
import ir.snapppay.wallet.io.wallet.account.transaction.TransactionSearchFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.12
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FindTransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public TransactionResponse load(final Long walletId, final Long accountId, final Long id) {
        // skip walletId,accountId this items fixed in specification
        Transaction transaction = transactionRepository.findById(id).orElseThrow(TransactionNotFoundException.instanceById(id));
        return transactionMapper.convert(transaction);
    }

    public Page<TransactionResponse> list(final Long walletId, final Long accountId, final TransactionSearchFilter searchFilter, final Pageable pageable) {
        Page<Transaction> transactions = transactionRepository.findAll(pageable);
        return transactions.map(transactionMapper::convert);
    }
}
