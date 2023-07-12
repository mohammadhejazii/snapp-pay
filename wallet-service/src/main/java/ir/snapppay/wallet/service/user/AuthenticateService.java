package ir.snapppay.wallet.service.user;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ir.snapppay.wallet.data.user.User;
import ir.snapppay.wallet.data.user.UserRepository;
import ir.snapppay.wallet.data.user.security.UserSecurityService;
import ir.snapppay.wallet.io.user.JwtResponse;
import ir.snapppay.wallet.io.user.LoginRequest;
import ir.snapppay.wallet.io.user.UserNotFoundException;
import ir.snapppay.wallet.io.user.UserPasswordNotMatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */


@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticateService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final UserSecurityService userSecurityService;

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.refresh-secret}")
    private String refreshSecret;

    public JwtResponse authenticate(final LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(UserNotFoundException.instanceByCellPhoneNumber(request.getUsername()));
        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw UserPasswordNotMatchException.wrongAuthenticationException();
        }
        UserDetails userDetails = userSecurityService.loadUserByUsername(user.getUsername());
        String jwt = jwtUtils.generateToken(userDetails);
        String refreshToken = jwtUtils.createRefreshToken(jwt);
        return JwtResponse.builder().token(jwt).refreshToken(refreshToken).build();
    }





}
