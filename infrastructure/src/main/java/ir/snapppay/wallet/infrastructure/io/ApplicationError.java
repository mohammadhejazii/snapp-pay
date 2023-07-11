package ir.snapppay.wallet.infrastructure.io;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.11
 */

@Getter
@Setter
@Builder
public class ApplicationError {


    private int status;


    private Instant timestamp;

    /**
     * Code of error.
     * every error has a unique code
     */
    private String messageCode;

    /**
     * Code of error.
     * every error has a unique code
     */
    private String errorCode;

    /**
     * message describing error, why error occurred and how to fix error
     */
    private String message;

    /**
     * message describing error in client local language
     */
    private String localizedMessage;


}
