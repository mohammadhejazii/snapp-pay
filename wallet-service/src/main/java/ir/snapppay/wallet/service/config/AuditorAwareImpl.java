package ir.snapppay.wallet.service.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.11
 */
public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        return Optional.of(0L);
    }
}
