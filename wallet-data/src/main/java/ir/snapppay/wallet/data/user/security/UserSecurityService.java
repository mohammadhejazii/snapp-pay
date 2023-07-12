package ir.snapppay.wallet.data.user.security;

import ir.snapppay.wallet.data.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.12
 */


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserSecurityService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(UserSecurity::new).orElseThrow(() -> new UsernameNotFoundException("User Not Found!"));
    }
}
