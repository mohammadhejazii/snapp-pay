package ir.snapppay.wallet.io.user;

import lombok.*;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUser {

    private String username;
    private String name;
    private String family;
    private String password;
    private String cellPhoneNumber;
    private String roles;

    public static RegisterUser of(final String username, final String password, final String name, final String family, final String cellPhoneNumber,final String roles) {
        return RegisterUser.builder()
            .username(username)
            .password(password)
            .name(name)
            .family(family)
            .cellPhoneNumber(cellPhoneNumber)
            .roles(roles)
            .build();
    }

}
