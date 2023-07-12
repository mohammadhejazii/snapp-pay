package ir.snapppay.wallet.service.wallet.account;

import ir.snapppay.wallet.data.wallet.account.Account;
import ir.snapppay.wallet.data.wallet.account.AccountRepository;
import ir.snapppay.wallet.io.wallet.account.AccountNotFoundException;
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
public class FindAccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public Page<AccountResponse> list(final Long walletId, final AccountSearchFilter searchFilter, final Pageable pageable) {

        // skip specification

        Page<Account> accounts = accountRepository.findAll(pageable);
        return accounts.map(accountMapper::convert);
    }

    public AccountResponse load(final Long walletId, final Long id) {
        Account account = accountRepository.findByIdAndWalletId(id, walletId).orElseThrow(AccountNotFoundException.instanceByWalletId(walletId));
        return accountMapper.convert(account);
    }
}
