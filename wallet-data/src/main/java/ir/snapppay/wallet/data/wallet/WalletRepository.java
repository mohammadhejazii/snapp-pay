package ir.snapppay.wallet.data.wallet;

import ir.snapppay.wallet.infrastructure.data.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.09
 */

@Repository
public interface WalletRepository extends BaseRepository<Wallet, Long> {
}
