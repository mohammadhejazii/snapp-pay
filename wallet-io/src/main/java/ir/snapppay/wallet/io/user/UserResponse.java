package ir.snapppay.wallet.io.user;

import ir.snapppay.wallet.infrastructure.io.AbstractAuditPortable;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.09
 */

@Getter
@Setter
@SuperBuilder
public class UserResponse extends AbstractAuditPortable<Long> {

    private String name;

    private String family;

    private String cellPhoneNumber;

    private String username;

}
