package ir.snapppay.wallet.service.wallet.account;

import ir.snapppay.wallet.data.wallet.Wallet;
import ir.snapppay.wallet.data.wallet.account.Account;
import ir.snapppay.wallet.io.wallet.account.AccountResponse;
import ir.snapppay.wallet.io.wallet.account.AccountState;
import ir.snapppay.wallet.io.wallet.account.RegisterAccount;
import ir.snapppay.wallet.service.wallet.WalletMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.12
 */

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountMapper {

    private final WalletMapper walletMapper;

    public AccountResponse convert(final Account account) {
        return AccountResponse.builder()
            .id(account.getId())
            .wallet(walletMapper.convert(account.getWallet()))
            .balance(account.getBalance())
            .state(account.getState())
            .number(account.getNumber())
            .build();
    }

    public Account convert(final Wallet wallet, final RegisterAccount request) {
        return Account.builder()
            .wallet(wallet)
            .state(AccountState.VALID)
            .number(request.getNumber())
            .balance(request.getBalance())
            .build();
    }
}
