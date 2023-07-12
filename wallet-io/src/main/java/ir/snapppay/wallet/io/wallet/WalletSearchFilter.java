package ir.snapppay.wallet.io.wallet;

import ir.snapppay.wallet.infrastructure.io.UserSearchFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Set;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.12
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WalletSearchFilter implements Serializable {

    private Set<WalletState> state;

    private String code;

    private String title;

    private UserSearchFilter user;

}
