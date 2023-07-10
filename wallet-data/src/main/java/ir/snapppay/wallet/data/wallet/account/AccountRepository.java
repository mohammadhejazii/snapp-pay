package ir.snapppay.wallet.data.wallet.account;

import ir.snapppay.wallet.data.wallet.Wallet;
import ir.snapppay.wallet.infrastructure.data.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */

@Repository
public interface AccountRepository extends BaseRepository<Account, Long> {
}
