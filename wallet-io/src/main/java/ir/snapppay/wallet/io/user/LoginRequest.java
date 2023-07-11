package ir.snapppay.wallet.io.user;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest implements Serializable {

    @NotNull(message = "login.username.not-null")
    @Size(min = 3, max = 50)
    private String username;

    @NotNull(message = "login.password.not-null")
    @Size(min = 8, max = 100)
    private String password;

}
