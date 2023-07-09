package ir.snapppay.wallet.data.wallet;

import ir.snapppay.wallet.infrastructure.data.AbstractAuditEntity;
import ir.snapppay.wallet.io.wallet.WalletState;
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
@Table(name = "wallet", uniqueConstraints = {@UniqueConstraint(name = "unique_wallet_code", columnNames = "wallet_code")})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Audited
@AuditOverride(forClass = AbstractAuditEntity.class)
@AuditTable("wallet_histories")
@Where(clause = "deleted = false")
public class Wallet extends AbstractAuditEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallet_seq")
    @SequenceGenerator(sequenceName = "wallet_seq", allocationSize = 1, name = "wallet_seq")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "wallet_code", nullable = false)
    private WalletState state;

    @Column(name = "wallet_code", nullable = false, updatable = false)
    private String code;

    @Column(name = "title")
    private String title;


}
