package ir.snapppay.wallet.service.user;

import ir.snapppay.wallet.data.user.User;
import ir.snapppay.wallet.io.user.RegisterUser;
import ir.snapppay.wallet.io.user.UserResponse;
import org.springframework.stereotype.Component;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */

@Component
public class UserMapper {


    public User convert(final RegisterUser request) {
        return User.builder()
            .username(request.getUsername())
            .cellPhoneNumber(request.getCellPhoneNumber())
            .name(request.getName())
            .family(request.getFamily())
            .Roles(request.getRoles())
            .build();
    }

    public UserResponse convert(final User user) {
        return UserResponse.builder()
            .id(user.getId())
            .username(user.getUsername())
            .cellPhoneNumber(user.getCellPhoneNumber())
            .name(user.getName())
            .family(user.getFamily())
            .build();
    }
}
