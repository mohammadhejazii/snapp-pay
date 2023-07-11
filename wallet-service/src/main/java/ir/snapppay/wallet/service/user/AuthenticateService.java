package ir.snapppay.wallet.service.user;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ir.snapppay.wallet.data.user.User;
import ir.snapppay.wallet.data.user.UserRepository;
import ir.snapppay.wallet.io.user.JwtResponse;
import ir.snapppay.wallet.io.user.LoginRequest;
import ir.snapppay.wallet.io.user.UserNotFoundException;
import ir.snapppay.wallet.io.user.UserPasswordNotMatchException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.refresh-secret}")
    private String refreshSecret;

    public JwtResponse authenticate(final LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(UserNotFoundException.instanceByCellPhoneNumber(request.getUsername()));
        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw UserPasswordNotMatchException.wrongAuthenticationException();
        }
        String jwt = createToken(user);
        String refreshToken = createRefreshToken(jwt);
        return JwtResponse.builder().token(jwt).refreshToken(refreshToken).build();
    }


    private String createToken(final User user) {
        ZonedDateTime expiration = ZonedDateTime.now().plus(10, ChronoUnit.MINUTES);
        return Jwts.builder()
            .setSubject(user.getUsername())
            .setIssuedAt(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)))
            .claim("user.id", user.getId())
            .claim("user.username", user.getUsername())
            .signWith(SignatureAlgorithm.HS256, secret)
            .setExpiration(Date.from(expiration.toInstant()))
            .compact();
    }


    public String createRefreshToken(final String token) {
        Instant expiration = LocalDateTime.now().toInstant(ZoneOffset.UTC).plus(100, ChronoUnit.DAYS);
        return Jwts.builder()
            .setSubject("refresh")
            .setIssuedAt(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC)))
            .claim("user.token", token)
            .signWith(SignatureAlgorithm.HS256, refreshSecret)
            .setExpiration(Date.from(expiration))
            .compact();
    }


}
