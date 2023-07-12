package ir.snapppay.wallet.data.wallet.account;

import ir.snapppay.wallet.infrastructure.data.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */

@Repository
public interface AccountRepository extends BaseRepository<Account, Long> {
    boolean existsByWalletId(final Long walletId);

    Optional<Account> findByWalletId(final Long walletId);

    Optional<Account> findByIdAndWalletId(final Long id,final Long walletId);
}
