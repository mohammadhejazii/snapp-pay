package ir.snapppay.wallet.io.wallet.account.transaction;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.12
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterTransaction implements Serializable {

    @NotNull
    private Long destinationAccount;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private TransactionType type;

}
