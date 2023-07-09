package ir.snapppay.wallet.infrastructure.data;

import java.io.Serializable;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.09
 */
public abstract class AbstractEntity<ID extends Serializable> implements Serializable, Cloneable {

    public abstract ID getId();

    protected abstract void setId(final ID id);

}
