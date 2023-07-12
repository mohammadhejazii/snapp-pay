package ir.snapppay.wallet.service.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ir.snapppay.wallet.data.user.User;
import ir.snapppay.wallet.io.user.AuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.12
 */
@Component
public class JwtUtils {

    @Value("${security.jwt.secret}")
    private String secret;

    @Value("${security.jwt.refresh-secret}")
    private String refreshSecret;


    public String createToken(final User user) {

        ZonedDateTime expiration = ZonedDateTime.now().plus(10, ChronoUnit.MINUTES);
        return Jwts.builder().setSubject(user.getUsername()).setIssuedAt(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC))).claim("user.id", user.getId()).claim("user.username", user.getUsername()).signWith(SignatureAlgorithm.HS256, secret).setExpiration(Date.from(expiration.toInstant())).compact();
    }


    public String createRefreshToken(final String token) {
        Instant expiration = LocalDateTime.now().toInstant(ZoneOffset.UTC).plus(100, ChronoUnit.DAYS);
        return Jwts.builder().setSubject("refresh").setIssuedAt(Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC))).claim("user.token", token).signWith(SignatureAlgorithm.HS256, refreshSecret).setExpiration(Date.from(expiration)).compact();
    }


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }


    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        String jwt = token;
        if (jwt.startsWith("Bearer")) {
            jwt = jwt.substring(7);
        }
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            throw AuthenticationException.of();
        }
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("authorities", userDetails.getAuthorities());
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)).signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
