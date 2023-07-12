package ir.snapppay.wallet.service.wallet;

import ir.snapppay.wallet.data.wallet.Wallet;
import ir.snapppay.wallet.data.wallet.WalletRepository;
import ir.snapppay.wallet.infrastructure.io.NotImplementedException;
import ir.snapppay.wallet.io.wallet.WalletNotFoundException;
import ir.snapppay.wallet.io.wallet.WalletResponse;
import ir.snapppay.wallet.io.wallet.WalletSearchFilter;
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
public class FindWalletService {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    public WalletResponse load(final Long id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(WalletNotFoundException.instanceById(id));
        return walletMapper.convert(wallet);
    }

    public Page<WalletResponse> list(final WalletSearchFilter searchFilter, final Pageable pageable) {
        throw NotImplementedException.of();
    }
}
