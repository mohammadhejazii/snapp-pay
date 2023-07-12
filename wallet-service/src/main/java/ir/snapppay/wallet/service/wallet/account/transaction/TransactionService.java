package ir.snapppay.wallet.service.wallet.account.transaction;

import ir.snapppay.wallet.io.wallet.account.transaction.RegisterTransaction;
import ir.snapppay.wallet.io.wallet.account.transaction.TransactionResponse;
import ir.snapppay.wallet.io.wallet.account.transaction.TransactionSearchFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.12
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionService {

    private final RegisterTransactionService registerTransactionService;
    private final FindTransactionService findTransactionService;


    @Transactional(readOnly = true)
    public TransactionResponse load(final Long walletId, final Long accountId, final Long id) {
        return findTransactionService.load(walletId, accountId, id);
    }

    @Transactional(readOnly = true)
    public Page<TransactionResponse> list(final Long walletId, final Long accountId, final TransactionSearchFilter searchFilter, final Pageable pageable) {
        return findTransactionService.list(walletId, accountId, searchFilter, pageable);
    }

    @Transactional
    public TransactionResponse register(final Long walletId, final Long accountId, final RegisterTransaction request) {
        return registerTransactionService.register(walletId, accountId, request);
    }
}
