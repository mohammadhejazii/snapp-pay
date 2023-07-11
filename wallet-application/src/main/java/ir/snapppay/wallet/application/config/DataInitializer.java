package ir.snapppay.wallet.application.config;

import ir.snapppay.wallet.infrastructure.io.UserSearchFilter;
import ir.snapppay.wallet.io.user.RegisterUser;
import ir.snapppay.wallet.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataInitializer implements CommandLineRunner {

    private final UserService userService;

    @Override
    public void run(final String... args) throws Exception {
        if (!userService.exists(UserSearchFilter.builder().cellPhoneNumber("09123456789").build())) {
            userService.register(RegisterUser.of("09123456789", "09123456789", "user1-name", "user1-family", "09123456789"));
        }
        if (!userService.exists(UserSearchFilter.builder().cellPhoneNumber("09123456780").build())) {
            userService.register(RegisterUser.of("09123456780", "09123456780", "user2-name", "user2-family", "09123456780"));
        }
        if (!userService.exists(UserSearchFilter.builder().cellPhoneNumber("09123456781").build())) {
            userService.register(RegisterUser.of("09123456781", "09123456781", "user3-name", "user3-family", "09123456781"));
        }
        if (!userService.exists(UserSearchFilter.builder().cellPhoneNumber("09123456782").build())) {
            userService.register(RegisterUser.of("09123456782", "09123456782", "user4-name", "user4-family", "09123456782"));
        }

    }
}
