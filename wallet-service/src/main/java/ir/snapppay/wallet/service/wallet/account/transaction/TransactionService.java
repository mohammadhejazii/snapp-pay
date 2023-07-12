package ir.snapppay.wallet.service.wallet.account.transaction;

import ir.snapppay.wallet.infrastructure.io.NotImplementedException;
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
public class TransactionService {
    public TransactionResponse load(final Long walletId, final Long accountId, final Long id) {
        throw NotImplementedException.of();
    }

    public Page<TransactionResponse> list(final Long walletId, final Long accountId, final TransactionSearchFilter searchFilter, final Pageable pageable) {
        throw NotImplementedException.of();
    }
}
