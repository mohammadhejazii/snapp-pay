package ir.snapppay.wallet.data.wallet.account.transaction;

import ir.snapppay.wallet.data.wallet.account.Account;
import ir.snapppay.wallet.infrastructure.data.AbstractAuditEntity;
import ir.snapppay.wallet.infrastructure.data.Money;
import ir.snapppay.wallet.io.wallet.account.transaction.TransactionState;
import ir.snapppay.wallet.io.wallet.account.transaction.TransactionType;
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
@Table(name = "transaction")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Audited
@AuditOverride(forClass = AbstractAuditEntity.class)
@AuditTable("transaction_histories")
@Where(clause = "deleted = false")
public class Transaction extends AbstractAuditEntity<Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    @SequenceGenerator(sequenceName = "transaction_seq", allocationSize = 1, name = "transaction_seq")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "source_account_id", foreignKey = @ForeignKey(name = "fk_transaction_sourceAccountId_account_id"))
    private Account source;

    @ManyToOne(optional = false)
    @JoinColumn(name = "destination_account_id", foreignKey = @ForeignKey(name = "fk_transaction_destinationAccountId_account_id"))
    private Account destination;

    @Embedded
    private Money amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private TransactionState state;

}
