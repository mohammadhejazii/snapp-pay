package ir.snapppay.wallet.application.controller.wallet.account.transaction;

import ir.snapppay.wallet.infrastructure.io.PagePortable;
import ir.snapppay.wallet.io.wallet.account.transaction.RegisterTransaction;
import ir.snapppay.wallet.io.wallet.account.transaction.TransactionResponse;
import ir.snapppay.wallet.io.wallet.account.transaction.TransactionSearchFilter;
import ir.snapppay.wallet.service.wallet.account.transaction.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.09
 */


@RestController
@RequestMapping("/wallets/{walletId}/accounts/{accountId}/transactions")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TransactionController {

    private final TransactionService transactionService;

    public ResponseEntity<PagePortable<TransactionResponse>> list(final @PathVariable Long walletId,
                                                                  final @PathVariable Long accountId,
                                                                  final @ModelAttribute TransactionSearchFilter searchFilter,
                                                                  final Pageable pageable) {
        Page<TransactionResponse> transactions = transactionService.list(walletId, accountId, searchFilter, pageable);
        return ResponseEntity.ok(PagePortable.of(transactions));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> load(final @PathVariable Long walletId,
                                                    final @PathVariable Long accountId,
                                                    final @PathVariable Long id) {
        TransactionResponse transaction = transactionService.load(walletId, accountId, id);
        return ResponseEntity.ok(transaction);
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> load(final @PathVariable Long walletId,
                                                    final @PathVariable Long accountId,
                                                    final @Validated @RequestBody RegisterTransaction request) {
        TransactionResponse transaction = transactionService.register(walletId, accountId, request);
        return ResponseEntity.ok(transaction);
    }


}
