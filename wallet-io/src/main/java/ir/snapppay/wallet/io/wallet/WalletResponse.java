package ir.snapppay.wallet.io.wallet;

import ir.snapppay.wallet.infrastructure.io.AbstractBasePortable;
import ir.snapppay.wallet.io.user.UserResponse;
import jakarta.persistence.Column;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.12
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WalletResponse extends AbstractBasePortable<Long> {

    private WalletState state;

    private String code;

    private String title;

    private UserResponse user;

}
