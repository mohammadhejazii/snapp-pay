package ir.snapppay.wallet.infrastructure.data;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.09
 */

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractBaseEntity<ID extends Serializable> extends AbstractEntity<ID> {

    /**
     * Number of updates applied to record
     * Used for handling concurrency so no two update can be applied to two loaded instance of an entity in two
     * different threads
     */
    @Version
    @Column(nullable = false)
    private Long version;


    /**
     * We do not DELETE a record physically!
     * if a record must be deleted, we just mark that record as DELETED
     */
    @Column(name = "deleted", nullable = false)
    private boolean deleted = false;

}
