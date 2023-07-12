package ir.snapppay.wallet.service.user;

import ir.snapppay.wallet.data.user.User;
import ir.snapppay.wallet.data.user.UserRepository;
import ir.snapppay.wallet.infrastructure.io.UserSearchFilter;
import ir.snapppay.wallet.io.user.UserNotFoundException;
import ir.snapppay.wallet.io.user.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FindUserService {

    private final UserRepository userRepository;
    private final UserSpecification userSpecification;
    private final UserMapper userMapper;

    public Page<UserResponse> list(final UserSearchFilter searchFilter,
                                   final Pageable pageable) {
        Specification<User> specification = userSpecification.of(searchFilter);
        Page<User> users = userRepository.findAll(specification, pageable);
        return users.map(userMapper::convert);
    }

    public Long count(final UserSearchFilter searchFilter) {
        Specification<User> specification = userSpecification.of(searchFilter);
        return userRepository.count(specification);
    }

    public UserResponse load(final Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException.instanceById(id));
        return userMapper.convert(user);
    }
}
