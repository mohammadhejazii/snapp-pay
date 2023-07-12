package ir.snapppay.wallet.io.wallet.account;

import ir.snapppay.wallet.infrastructure.data.Money;
import ir.snapppay.wallet.infrastructure.io.AbstractBasePortable;
import ir.snapppay.wallet.io.wallet.WalletResponse;
import ir.snapppay.wallet.io.wallet.WalletSearchFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Set;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.12
 */

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class AccountSearchFilter implements Serializable {

    private String number;

    private WalletSearchFilter wallet;

    private Money balance;

    private Set<AccountState> state;

}
