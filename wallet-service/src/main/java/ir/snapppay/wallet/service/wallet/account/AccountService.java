package ir.snapppay.wallet.service.wallet.account;

import ir.snapppay.wallet.infrastructure.io.NotImplementedException;
import ir.snapppay.wallet.io.wallet.account.AccountResponse;
import ir.snapppay.wallet.io.wallet.account.AccountSearchFilter;
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
public class AccountService {
    public Page<AccountResponse> list(final Long walletId, final AccountSearchFilter searchFilter, final Pageable pageable) {
        throw NotImplementedException.of();
    }

    public AccountResponse load(final Long walletId, final Long id) {
        throw NotImplementedException.of();
    }
}
