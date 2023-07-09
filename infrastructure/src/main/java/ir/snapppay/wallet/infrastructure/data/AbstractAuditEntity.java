package ir.snapppay.wallet.infrastructure.data;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditOverride;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.09
 */

@Getter
@Setter
@AuditOverride(forClass = AbstractAuditEntity.class)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditEntity<ID extends Serializable> extends AbstractBaseEntity<ID> {


    @CreatedBy
    @Column(name = "creator", nullable = false, updatable = false)
    private Long creator;

    @CreatedDate
    @Column(name = "creation_date_time", nullable = false, updatable = false)
    private Instant creationDateTime;


    @LastModifiedBy
    @Column(name = "modifier")
    private Long modifier;

    @LastModifiedDate
    @Column(name = "modification_date_time")
    private Instant modificationDateTime;

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }


    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbstractBaseEntity that = (AbstractBaseEntity) obj;
        return Objects.equals(this.getId(), that.getId());
    }

}
