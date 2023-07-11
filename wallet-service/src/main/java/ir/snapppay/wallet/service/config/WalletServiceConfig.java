package ir.snapppay.wallet.service.config;

import ir.snapppay.wallet.data.config.ImportWalletDataModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@ImportWalletDataModule
@ComponentScan(basePackages = {"ir.snapppay.wallet.service"})
@PropertySource("classpath:wallet-service.application.properties")
public class WalletServiceConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuditorAwareImpl auditorAware(){
        return new AuditorAwareImpl();
    }


}
