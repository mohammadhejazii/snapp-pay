package ir.snapppay.wallet.infrastructure.io;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.09
 */

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractBasePortable<ID extends Serializable> implements Serializable {

    private ID id;

    private Long version;

}
