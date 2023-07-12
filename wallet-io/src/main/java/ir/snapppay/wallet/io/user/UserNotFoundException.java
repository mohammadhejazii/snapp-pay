package ir.snapppay.wallet.io.user;

import ir.snapppay.wallet.infrastructure.io.BaseBusinessException;
import org.springframework.http.HttpStatus;

import java.util.function.Supplier;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */
public class UserNotFoundException extends BaseBusinessException implements Supplier<UserNotFoundException> {
    private String code;

    private UserNotFoundException(String code) {
        this.code = code;
    }

    public static UserNotFoundException instanceById(Long id) {
        UserNotFoundException exception = new UserNotFoundException("error.user.id.not-found.exception");
        exception.addMessageArg("id", id);
        return exception;
    }

    public static UserNotFoundException instanceByUserName(String username) {
        UserNotFoundException exception = new UserNotFoundException("error.user.username.not-found.exception");
        exception.addMessageArg("username", username);
        return exception;
    }

    public static UserNotFoundException instanceByCellPhoneNumber(final String cellPhoneNumber) {
        UserNotFoundException exception = new UserNotFoundException("error.user.cellPhoneNumber.not-found.exception");
        exception.addMessageArg("cellPhoneNumber", cellPhoneNumber);
        return exception;
    }

    @Override
    public int getHttpStatus() {
        return HttpStatus.NOT_FOUND.value();
    }

    @Override
    public String getMessageKey() {
        return code;
    }

    @Override
    public UserNotFoundException get() {
        return this;
    }

}
