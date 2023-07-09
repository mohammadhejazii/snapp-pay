package ir.snapppay.wallet.data.user;

import ir.snapppay.wallet.infrastructure.data.BaseRepository;
import org.springframework.stereotype.Repository;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.09
 */

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
}
