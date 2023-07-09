package ir.snapppay.wallet.infrastructure.io;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.09
 */

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@ToString
public class AbstractAuditPortable<ID extends Serializable> extends AbstractBasePortable<ID> {

    private Boolean deleted;

    private Instant creationDateTime;

    private Instant modificationDateTime;

    private Long creator;

    private Long modifier;

}
