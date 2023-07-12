package ir.snapppay.wallet.service.wallet;

import ir.snapppay.wallet.io.wallet.WalletResponse;
import ir.snapppay.wallet.io.wallet.WalletSearchFilter;
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
public class WalletService {

    private final FindWalletService findWalletService;

    @Transactional(readOnly = true)
    public Page<WalletResponse> list(final WalletSearchFilter searchFilter, final Pageable pageable) {
        return findWalletService.list(searchFilter,pageable);
    }

    @Transactional(readOnly = true)
    public WalletResponse load(final Long id) {
        return findWalletService.load(id);
    }
}
