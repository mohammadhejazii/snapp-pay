package ir.snapppay.wallet.io.wallet.account.transaction;

import ir.snapppay.wallet.infrastructure.data.Money;
import ir.snapppay.wallet.infrastructure.io.AbstractBasePortable;
import ir.snapppay.wallet.io.wallet.account.AccountSearchFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
public class TransactionSearchFilter extends AbstractBasePortable<Long> {

    private AccountSearchFilter source;

    private AccountSearchFilter destination;

    private Money amount;

    private Set<TransactionType> type;

    private Set<TransactionState> state;

}
