package ir.snapppay.wallet.service.user;

import ir.snapppay.wallet.data.user.User;
import ir.snapppay.wallet.data.user.User_;
import ir.snapppay.wallet.infrastructure.io.UserSearchFilter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */

@Service
public class UserSpecification {


    public Specification<User> of(final UserSearchFilter searchFilter) {
        Specification<User> specification = (root, criteriaQuery, criteriaBuilder) -> criteriaQuery.getRestriction();

        if (searchFilter.getUsername() != null) {
            specification = specification.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.USERNAME), searchFilter.getUsername()));
        }
        if (searchFilter.getName() != null) {
            specification = specification.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(User_.NAME), likeOperator(searchFilter.getName())));
        }

        if (searchFilter.getFamily() != null) {
            specification = specification.and((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(User_.FAMILY), likeOperator(searchFilter.getFamily())));
        }

        if (searchFilter.getCellPhoneNumber() != null) {
            specification = specification.and((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(User_.CELL_PHONE_NUMBER), searchFilter.getCellPhoneNumber()));
        }

        return specification;
    }


    private String likeOperator(final String value) {
        return "%" + value + "%";
    }

}
