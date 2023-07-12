package ir.snapppay.wallet.service.user;

import ir.snapppay.wallet.infrastructure.io.UserSearchFilter;
import ir.snapppay.wallet.io.user.RegisterUser;
import ir.snapppay.wallet.io.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final RegisterUserService registerUserService;
    private final FindUserService findUserService;

    @Transactional
    public UserResponse register(final RegisterUser request) {
        return registerUserService.register(request);
    }


    @Transactional(readOnly = true)
    public Page<UserResponse> list(final UserSearchFilter searchFilter, final Pageable pageable) {
        return findUserService.list(searchFilter, pageable);
    }

    @Transactional(readOnly = true)
    public Long count(final UserSearchFilter searchFilter) {
        return findUserService.count(searchFilter);
    }

    @Transactional(readOnly = true)
    public boolean exists(final UserSearchFilter searchFilter) {
        return count(searchFilter) > 0;
    }

    @Transactional(readOnly = true)
    public UserResponse load(final Long id) {
        return findUserService.load(id);
    }
}
