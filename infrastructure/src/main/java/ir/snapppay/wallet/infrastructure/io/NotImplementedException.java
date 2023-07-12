package ir.snapppay.wallet.infrastructure.io;

import java.util.function.Supplier;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.12
 */
public class NotImplementedException extends BaseBusinessException implements Supplier<NotImplementedException> {

    private NotImplementedException(){

    }

    public static NotImplementedException of(){
        return new NotImplementedException();
    }


    @Override
    public String getMessageKey() {
        return "not-implemented.exception";
    }

    @Override
    public NotImplementedException get() {
        return this;
    }
}
