package ir.snapppay.wallet.io.wallet.account;

import ir.snapppay.wallet.infrastructure.data.Money;
import ir.snapppay.wallet.infrastructure.io.AbstractBasePortable;
import ir.snapppay.wallet.io.wallet.WalletResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.12
 */

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse extends AbstractBasePortable<Long> {

    private String number;

    private WalletResponse wallet;

    private Money balance;

    private AccountState state;

}
