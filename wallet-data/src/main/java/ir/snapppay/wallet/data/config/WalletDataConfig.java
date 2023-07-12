package ir.snapppay.wallet.data.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */

@Configuration
@EnableJpaRepositories(basePackages = {"ir.snapppay.wallet.data"}, repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@EntityScan(basePackages = {"ir.snapppay.wallet.data"})
@ComponentScan(basePackages = {"ir.snapppay.wallet.data"})
@EnableJpaAuditing
class WalletDataConfig {
}
