package ir.snapppay.wallet.io.wallet.account;

import ir.snapppay.wallet.infrastructure.data.Money;
import lombok.*;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.12
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterAccount {

    private Money balance;

    private String number;


}
