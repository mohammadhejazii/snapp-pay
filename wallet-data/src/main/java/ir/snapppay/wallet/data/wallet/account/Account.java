package ir.snapppay.wallet.data.wallet.account;

import ir.snapppay.wallet.data.wallet.Wallet;
import ir.snapppay.wallet.infrastructure.data.AbstractAuditEntity;
import ir.snapppay.wallet.infrastructure.data.Money;
import ir.snapppay.wallet.io.wallet.account.AccountState;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.09
 */

@Entity
@Table(name = "account", uniqueConstraints = {
        @UniqueConstraint(name = "unique_account_walletId_currency", columnNames = {"wallet_id", "currency_unit"}),
        @UniqueConstraint(name = "unique_account_number", columnNames = {"number"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Audited
@AuditOverride(forClass = AbstractAuditEntity.class)
@AuditTable("account_histories")
@Where(clause = "deleted = false")
public class Account extends AbstractAuditEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_seq")
    @SequenceGenerator(sequenceName = "account_seq", allocationSize = 1, name = "account_seq")
    private Long id;

    @Column(name = "number", length = 16, nullable = false, unique = true)
    private String number;

    @ManyToOne(optional = false)
    @JoinColumn(name = "wallet_id", foreignKey = @ForeignKey(name = "fk_account_walletId_wallet_id"))
    private Wallet wallet;

    @Embedded
    private Money balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private AccountState state;

}
