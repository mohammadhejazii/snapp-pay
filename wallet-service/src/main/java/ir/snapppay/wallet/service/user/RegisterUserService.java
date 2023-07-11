package ir.snapppay.wallet.service.user;

import ir.snapppay.wallet.data.user.User;
import ir.snapppay.wallet.data.user.UserRepository;
import ir.snapppay.wallet.io.user.RegisterUser;
import ir.snapppay.wallet.io.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterUserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder;

    public UserResponse register(final RegisterUser request) {
        User user = userMapper.convert(request);
        String password = encoder.encode(request.getPassword());
        user.setPassword(password);
        user = userRepository.save(user);
        return userMapper.convert(user);
    }
}
