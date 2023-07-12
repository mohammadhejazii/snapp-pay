package ir.snapppay.wallet.application.config;

import ir.snapppay.wallet.data.user.User;
import ir.snapppay.wallet.data.user.UserRepository;
import ir.snapppay.wallet.data.wallet.Wallet;
import ir.snapppay.wallet.data.wallet.WalletRepository;
import ir.snapppay.wallet.data.wallet.account.Account;
import ir.snapppay.wallet.data.wallet.account.AccountRepository;
import ir.snapppay.wallet.infrastructure.data.Money;
import ir.snapppay.wallet.infrastructure.io.UserSearchFilter;
import ir.snapppay.wallet.io.user.RegisterUser;
import ir.snapppay.wallet.io.user.UserNotFoundException;
import ir.snapppay.wallet.io.user.UserResponse;
import ir.snapppay.wallet.io.wallet.RegisterWallet;
import ir.snapppay.wallet.io.wallet.WalletNotFoundException;
import ir.snapppay.wallet.io.wallet.WalletResponse;
import ir.snapppay.wallet.io.wallet.account.AccountNotFoundException;
import ir.snapppay.wallet.io.wallet.account.AccountResponse;
import ir.snapppay.wallet.io.wallet.account.RegisterAccount;
import ir.snapppay.wallet.service.user.UserService;
import ir.snapppay.wallet.service.wallet.WalletMapper;
import ir.snapppay.wallet.service.wallet.account.AccountMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public void run(final String... args) throws Exception {

        UserResponse user2 = initializeUser(RegisterUser.of("09123456780", "09123456780", "user2-name", "user2-family", "09123456780"));
        UserResponse user3 = initializeUser(RegisterUser.of("09123456781", "09123456781", "user3-name", "user3-family", "09123456781"));
        UserResponse user4 = initializeUser(RegisterUser.of("09123456782", "09123456782", "user4-name", "user4-family", "09123456782"));
        UserResponse user1 = initializeUser(RegisterUser.of("09123456783", "09123456783", "user1-name", "user1-family", "09123456783"));

        WalletResponse walletUser1 = initializeWallet(user1);
        WalletResponse walletUser2 = initializeWallet(user2);
        WalletResponse walletUser3 = initializeWallet(user3);
        WalletResponse walletUser4 = initializeWallet(user4);

        AccountResponse accountUser1 = initializeAccount(walletUser1);
        AccountResponse accountUser2 = initializeAccount(walletUser2);
        AccountResponse accountUser3 = initializeAccount(walletUser3);
        AccountResponse accountUser4 = initializeAccount(walletUser4);

    }

    private AccountResponse initializeAccount(final WalletResponse wallet) {
        if (accountRepository.existsByWalletId(wallet.getId())) {
            Account account = accountRepository.findByWalletId(wallet.getId()).orElseThrow(AccountNotFoundException.instanceByWalletId(wallet.getId()));
            return accountMapper.convert(account);
        } else {
            Wallet candidateWallet = walletRepository.findById(wallet.getId()).orElseThrow(WalletNotFoundException.instanceById(wallet.getId()));
            Account account = accountMapper.convert(candidateWallet, RegisterAccount.builder().balance(Money.of(BigDecimal.valueOf(100000000L))).number(wallet.getCode()).build());
            account = accountRepository.save(account);
            return accountMapper.convert(account);
        }
    }

    private WalletResponse initializeWallet(final UserResponse user) {
        if (walletRepository.existsByUserId(user.getId())) {
            Wallet wallet = walletRepository.findByUserId(user.getId()).orElseThrow(WalletNotFoundException.instanceByUserId(user.getId()));
            return walletMapper.convert(wallet);
        } else {
            User candidateUser = userRepository.findById(user.getId()).orElseThrow(UserNotFoundException.instanceById(user.getId()));
            Wallet wallet = walletMapper.convert(candidateUser, RegisterWallet.builder().code(candidateUser.getCellPhoneNumber()).title(candidateUser.getCellPhoneNumber()).build());
            wallet = walletRepository.save(wallet);
            return walletMapper.convert(wallet);
        }
    }

    private UserResponse initializeUser(final RegisterUser request) {
        if (!userService.exists(UserSearchFilter.builder().cellPhoneNumber(request.getCellPhoneNumber()).build())) {
            return userService.register(request);
        } else {
            return userService.list(UserSearchFilter.builder().cellPhoneNumber(request.getCellPhoneNumber()).build(), Pageable.unpaged()).getContent().get(0);
        }
    }
}
