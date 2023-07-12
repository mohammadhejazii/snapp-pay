package ir.snapppay.wallet.io.user;

import ir.snapppay.wallet.infrastructure.io.BaseBusinessException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.11
 */
public class AuthenticationException extends BaseBusinessException implements Supplier<AuthenticationException> {

    private String code;


    private AuthenticationException(String code) {
        this.code = code;
    }

    public static AuthenticationException of() {
        return new AuthenticationException("authentication.exception");
    }

    @Override
    public String getMessageKey() {
        return code;
    }

    @Override
    public AuthenticationException get() {
        return this;
    }


    @Override
    public int getHttpStatus() {
        return HttpStatus.UNAUTHORIZED.value();
    }
}

