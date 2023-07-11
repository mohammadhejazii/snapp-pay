package ir.snapppay.wallet.application.controller.user;

import ir.snapppay.wallet.io.user.JwtResponse;
import ir.snapppay.wallet.io.user.LoginRequest;
import ir.snapppay.wallet.service.user.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

    private final AuthenticateService authenticateService;


    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> authenticate(@Validated @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticateService.authenticate(loginRequest));
    }


}
