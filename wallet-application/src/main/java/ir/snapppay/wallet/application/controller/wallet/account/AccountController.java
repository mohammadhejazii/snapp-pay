package ir.snapppay.wallet.application.controller.wallet.account;

import ir.snapppay.wallet.infrastructure.io.PagePortable;
import ir.snapppay.wallet.io.wallet.account.AccountResponse;
import ir.snapppay.wallet.io.wallet.account.AccountSearchFilter;
import ir.snapppay.wallet.service.wallet.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.09
 */


@RestController
@RequestMapping("/wallets/{walletId}/accounts")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<PagePortable<AccountResponse>> list(final @PathVariable Long walletId,
                                                              final @ModelAttribute AccountSearchFilter searchFilter,
                                                              final Pageable pageable) {
        Page<AccountResponse> accounts = accountService.list(walletId, searchFilter, pageable);
        return ResponseEntity.ok(PagePortable.of(accounts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> load(final @PathVariable Long walletId,
                                                final @PathVariable Long id) {
        AccountResponse account = accountService.load(walletId, id);
        return ResponseEntity.ok(account);
    }

}
