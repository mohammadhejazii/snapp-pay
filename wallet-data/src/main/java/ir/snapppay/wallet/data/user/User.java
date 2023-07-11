package ir.snapppay.wallet.data.user;

import ir.snapppay.wallet.infrastructure.data.AbstractAuditEntity;
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
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(name = "users_cellphoneNumber", columnNames = "cell_phone_number"),
    @UniqueConstraint(name = "users_userName", columnNames = "user_name"),
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Audited
@AuditOverride(forClass = AbstractAuditEntity.class)
@AuditTable("users_histories")
@Where(clause = "deleted = false")
public class User extends AbstractAuditEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(sequenceName = "users_seq", allocationSize = 1, name = "users_seq")
    private Long id;

    @Column(name = "cell_phone_number", length = 11, nullable = false)
    private String cellPhoneNumber;

    @Column(name = "user_name", length = 32, nullable = false)
    private String username;

    @Column(name = "name", length = 32, nullable = false)
    private String name;

    @Column(name = "family", length = 32, nullable = false)
    private String family;

    @Column(name = "password", nullable = false)
    private String password;


}
