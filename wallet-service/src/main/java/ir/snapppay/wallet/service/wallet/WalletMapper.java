package ir.snapppay.wallet.service.wallet;

import ir.snapppay.wallet.data.user.User;
import ir.snapppay.wallet.data.wallet.Wallet;
import ir.snapppay.wallet.io.wallet.RegisterWallet;
import ir.snapppay.wallet.io.wallet.WalletResponse;
import ir.snapppay.wallet.io.wallet.WalletState;
import ir.snapppay.wallet.service.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.12
 */

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WalletMapper {

    private final UserMapper userMapper;


    public Wallet convert(final User user, final RegisterWallet request) {
        return Wallet.builder()
            .title(request.getTitle())
            .state(WalletState.ACTIVE)
            .code(request.getCode())
            .user(user)
            .build();
    }

    public WalletResponse convert(final Wallet wallet) {
        return WalletResponse.builder()
            .id(wallet.getId())
            .title(wallet.getTitle())
            .state(wallet.getState())
            .code(wallet.getCode())
            .user(userMapper.convert(wallet.getUser()))
            .build();
    }
}
