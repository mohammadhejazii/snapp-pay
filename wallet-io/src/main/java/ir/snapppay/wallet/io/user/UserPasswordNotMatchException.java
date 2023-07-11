package ir.snapppay.wallet.io.user;

import ir.snapppay.wallet.infrastructure.io.BaseBusinessException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.11
 */
public class UserPasswordNotMatchException extends BaseBusinessException implements Supplier<UserPasswordNotMatchException> {

    private String code;


    private UserPasswordNotMatchException(String code) {
        this.code = code;
    }

    public static UserPasswordNotMatchException lastPasswordIsWrong() {
        UserPasswordNotMatchException exception = new UserPasswordNotMatchException("error.user-last-password.not-match.exception");
        return exception;
    }


    public static UserPasswordNotMatchException confirmPasswordNotMatchWithPassword() {
        UserPasswordNotMatchException exception = new UserPasswordNotMatchException("error.user-confirm-password.not-match.exception");
        return exception;
    }

    public static UserPasswordNotMatchException wrongAuthenticationException() {
        UserPasswordNotMatchException exception = new UserPasswordNotMatchException("error.username-password.not-match.exception");
        return exception;
    }


    @Override
    public String getMessageKey() {
        return code;
    }

    @Override
    public UserPasswordNotMatchException get() {
        return this;
    }


    @Override
    public int getHttpStatus() {
        return HttpStatus.UNAUTHORIZED.value();
    }
}

