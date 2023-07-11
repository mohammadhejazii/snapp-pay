package ir.snapppay.wallet.application.config;

import ir.snapppay.wallet.infrastructure.io.ApplicationError;
import ir.snapppay.wallet.infrastructure.io.BaseBusinessException;
import ir.snapppay.wallet.infrastructure.utils.BaseUtility;
import ir.snapppay.wallet.infrastructure.utils.MessageFiller;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Locale;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.11
 */

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebExceptionHandler extends ResponseEntityExceptionHandler implements ErrorController {

    private final MessageSource messageSource;


    @ExceptionHandler(BaseBusinessException.class)
    public ResponseEntity<Object> handleBaseBusinessException(final BaseBusinessException exception, final HttpServletRequest request) {
        ApplicationError error = initialize(exception, messageSource, request.getHeader("Accept-Language"), MessageFiller.INSTANCE);
        return ResponseEntity.status(error.getStatus()).body(error);
    }


    private ApplicationError initialize(final BaseBusinessException exception,
                                        final MessageSource messageSource,
                                        final String localeKey,
                                        final MessageFiller formatter) {
        Locale locale = Locale.ENGLISH;
        String loadedMessage = BaseUtility.getMessage(
            messageSource,
            exception.getMessageKey(),
            locale,
            null
        );
        loadedMessage = formatter.fill(loadedMessage, exception.getMessageArgs());


        ApplicationError presentableApiError = ApplicationError.builder()
            .status(exception.getHttpStatus())
            .message(loadedMessage)
            .errorCode(exception.getErrorCode())
            .messageCode(exception.getMessageKey())
            .timestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC))
            .build();


        if (localeKey != null && !localeKey.isEmpty()) {
            locale = new Locale(localeKey);

            String localizedMessage = BaseUtility.getMessage(
                messageSource,
                exception.getMessageKey(),
                locale,
                null
            );

            localizedMessage = formatter.fill(localizedMessage, exception.getMessageArgs());
            presentableApiError.setLocalizedMessage(localizedMessage);
        }

        return presentableApiError;
    }


}
