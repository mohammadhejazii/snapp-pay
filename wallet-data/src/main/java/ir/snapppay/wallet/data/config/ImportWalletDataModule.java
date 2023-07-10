package ir.snapppay.wallet.data.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */

@Target(value = TYPE)
@Retention(value = RUNTIME)
@Import(value = WalletDataConfig.class)
@Documented
public @interface ImportWalletDataModule {
}
