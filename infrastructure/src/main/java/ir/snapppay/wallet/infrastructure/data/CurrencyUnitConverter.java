package ir.snapppay.wallet.infrastructure.data;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.joda.money.CurrencyUnit;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.09
 */

@Converter(autoApply = true)
public class CurrencyUnitConverter implements AttributeConverter<CurrencyUnit, String> {

    public final static CurrencyUnitConverter INSTANCE = new CurrencyUnitConverter();

    @Override
    public String convertToDatabaseColumn(final CurrencyUnit attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getCode();
    }

    @Override
    public CurrencyUnit convertToEntityAttribute(final String dbData) {
        if (dbData == null) {
            return null;
        }
        return CurrencyUnit.of(dbData);
    }

}
