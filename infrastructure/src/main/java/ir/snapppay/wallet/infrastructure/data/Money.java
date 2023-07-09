package ir.snapppay.wallet.infrastructure.data;

import ir.snapppay.wallet.infrastructure.utils.Selectors;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.Objects;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.09
 */


@Embeddable
@Getter
@Setter
@ToString
public final class Money {


    public static final CurrencyUnit IRAN = CurrencyUnit.of(new Locale("fa", "IR"));

    /**
     * holds amount of money
     */
    @Column(name = "amount")
    private BigDecimal amount;

    /**
     * currency of money
     */
    @Column(name = "currency_unit", length = 3)
    @Convert(converter = CurrencyUnitConverter.class)
    private CurrencyUnit currency;

    /**
     * Why this is defined? if we need a Money instance with zero amount, simple we must call completed version
     * that gets currency and amount with amount of zero
     *
     * @param currencyUnit unit of money
     * @return a new instance of Money with specified currencyUnit and zero amount
     */
    public static Money of(final CurrencyUnit currencyUnit) {
        Money money = new Money();
        money.setCurrency(currencyUnit);
        money.setAmount(BigDecimal.ZERO);
        return money;
    }

    /**
     * creates an instance of Money with desired amount and currency
     *
     * @param currencyUnit currency of money
     * @param amount       amount of money. amount cannot to negative
     * @return an instance of Money with provided currency and amount
     */
    public static Money of(final CurrencyUnit currencyUnit, final BigDecimal amount) {
        Money money = new Money();
        money.setAmount(amount);
        money.setCurrency(currencyUnit);
        return money;
    }

    public static Money of(final BigDecimal amount) {
        Money money = new Money();
        money.setAmount(amount);
        money.setCurrency(IRAN);
        return money;
    }

    public static Money of(final BigMoney request) {
        Money money = new Money();
        if (request != null) {
            money.setAmount(Selectors.getOrDefault(request.getAmount(), BigDecimal.ZERO));
            money.setCurrency(Selectors.getOrDefault(request.getCurrencyUnit(), IRAN));
        } else {
            money = Money.of(IRAN, BigDecimal.ZERO);
        }
        return money;
    }


    public static Money of(final org.joda.money.Money request) {
        Money money = new Money();
        if (request != null) {
            money.setAmount(Selectors.getOrDefault(request.getAmount(), BigDecimal.ZERO));
            money.setCurrency(Selectors.getOrDefault(request.getCurrencyUnit(), IRAN));
        } else {
            money = Money.of(IRAN, BigDecimal.ZERO);
        }
        return money;
    }


    public void plus(final Money that) {
        if (!Objects.equals(this.getCurrency(), that.getCurrency())) {
            throw new RuntimeException(String.format(
                    "Unable to add money with currency %s to this money with currency %s",
                    that.getCurrency(),
                    this.getCurrency()
            ));
        }
        BigDecimal sum = this.getAmount().add(that.getAmount());
        setAmount(sum);
    }


    public void minus(final Money that) {
        if (!Objects.equals(this.getCurrency(), that.getCurrency())) {
            throw new RuntimeException(String.format(
                    "Unable to add money with currency %s to this money with currency %s",
                    that.getCurrency(),
                    this.getCurrency()
            ));
        }
        BigDecimal sum = this.getAmount().subtract(that.getAmount());
        setAmount(sum);
    }

    /**
     * @return hash code of currency unit!!!
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(getCurrency());
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Money that = (Money) obj;
        return Objects.equals(this.getCurrency(), that.getCurrency());
    }


    public org.joda.money.Money toJodaMoney() {
        if (getCurrency() != null && getAmount() != null) {
            return org.joda.money.Money.of(getCurrency(), getAmount());
        }
        return org.joda.money.Money.of(IRAN, BigDecimal.ZERO);
    }

    public org.joda.money.BigMoney toJodaBigMoney() {
        if (getCurrency() != null && getAmount() != null) {
            return org.joda.money.BigMoney.of(getCurrency(), getAmount());
        }
        return org.joda.money.BigMoney.of(IRAN, BigDecimal.ZERO);
    }

}
