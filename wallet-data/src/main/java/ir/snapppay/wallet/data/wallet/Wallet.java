package ir.snapppay.wallet.data.wallet;

import ir.snapppay.wallet.data.user.User;
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
@Table(name = "wallet", uniqueConstraints = {@UniqueConstraint(name = "unique_wallet_code", columnNames = "code")})
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
    @Column(name = "state", nullable = false)
    private WalletState state;

    @Column(name = "code", nullable = false, updatable = false)
    private String code;

    @Column(name = "title")
    private String title;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_wallet_userId_user_id"))
    private User user;


}
