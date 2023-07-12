package ir.snapppay.wallet.application.config;

import ir.snapppay.wallet.data.user.security.UserSecurityService;
import ir.snapppay.wallet.io.user.AuthenticationException;
import ir.snapppay.wallet.service.user.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.11
 */

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserSecurityService userSecurityService;
    private final JwtUtils jwtUtils;

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain filterChain) throws ServletException, IOException {
        if (!skip(request.getRequestURI())) {
            String authorizationToken = request.getHeader(HttpHeaders.AUTHORIZATION);
            String username = jwtUtils.extractUsername(authorizationToken);
            UserDetails userDetails = userSecurityService.loadUserByUsername(username);
            if (jwtUtils.validateToken(authorizationToken, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                throw AuthenticationException.of();
            }
        }
        filterChain.doFilter(request, response);
    }


    private boolean skip(String path) {
        boolean skip = false;
        Set<String> paths = Set.of("/authentication/authenticate",
            "/authentication/*",
            "/api/authentication/authenticate",
            "/api/authentication/*",
            "/api/v1/auth/*",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/*",
            "/swagger-resources",
            "/swagger-resources/*",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/*",
            "/webjars/*",
            "/swagger-ui.html");

        for (String item : paths) {
            Pattern pattern = Pattern.compile(item, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(path);
            if (matcher.find()) {
                skip = true;
                break;
            }
        }
        return skip;
    }

}
