package ir.snapppay.wallet.application.controller.user;

import ir.snapppay.wallet.infrastructure.io.PagePortable;
import ir.snapppay.wallet.infrastructure.io.UserSearchFilter;
import ir.snapppay.wallet.io.user.UserResponse;
import ir.snapppay.wallet.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.09
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<PagePortable<UserResponse>> list(final @ModelAttribute UserSearchFilter searchFilter,
                                                           final Pageable pageable) {
        Page<UserResponse> users = userService.list(searchFilter, pageable);
        return ResponseEntity.ok(PagePortable.of(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> load(final @PathVariable Long id) {
        UserResponse user = userService.load(id);
        return ResponseEntity.ok(user);
    }


}
