package ir.snapppay.wallet.service.wallet.account;

import ir.snapppay.wallet.infrastructure.io.NotImplementedException;
import ir.snapppay.wallet.io.wallet.account.AccountResponse;
import ir.snapppay.wallet.io.wallet.account.AccountSearchFilter;
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
public class AccountService {
    private final FindAccountService findAccountService;

    @Transactional(readOnly = true)
    public Page<AccountResponse> list(final Long walletId, final AccountSearchFilter searchFilter, final Pageable pageable) {
        return findAccountService.list(walletId,searchFilter,pageable);
    }

    @Transactional(readOnly = true)
    public AccountResponse load(final Long walletId, final Long id) {
        return findAccountService.load(walletId,id);
    }
}
