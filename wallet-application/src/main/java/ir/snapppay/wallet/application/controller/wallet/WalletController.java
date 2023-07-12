package ir.snapppay.wallet.application.controller.wallet;

import ir.snapppay.wallet.infrastructure.io.PagePortable;
import ir.snapppay.wallet.io.wallet.WalletResponse;
import ir.snapppay.wallet.io.wallet.WalletSearchFilter;
import ir.snapppay.wallet.service.wallet.WalletService;
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
@RequestMapping("/wallets")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WalletController {

    private final WalletService walletService;

    public ResponseEntity<PagePortable<WalletResponse>> list(final @ModelAttribute WalletSearchFilter searchFilter,
                                                             final Pageable pageable) {
        Page<WalletResponse> wallets = walletService.list(searchFilter, pageable);
        return ResponseEntity.ok(PagePortable.of(wallets));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletResponse> load(final @PathVariable Long id) {
        WalletResponse wallet = walletService.load(id);
        return ResponseEntity.ok(wallet);
    }


}
