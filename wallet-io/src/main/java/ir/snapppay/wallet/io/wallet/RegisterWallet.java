package ir.snapppay.wallet.io.wallet;

import jakarta.persistence.Column;
import lombok.*;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.12
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterWallet {

    private String code;

    private String title;


}
