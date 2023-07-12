package ir.snapppay.wallet.io.wallet.account.transaction;

import ir.snapppay.wallet.infrastructure.data.Money;
import ir.snapppay.wallet.infrastructure.io.AbstractBasePortable;
import ir.snapppay.wallet.io.wallet.account.AccountResponse;
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
public class TransactionResponse extends AbstractBasePortable<Long> {

    private AccountResponse source;

    private AccountResponse destination;

    private Money amount;

    private TransactionType type;

    private TransactionState state;

}
