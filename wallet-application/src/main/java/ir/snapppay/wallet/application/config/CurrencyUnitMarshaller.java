package ir.snapppay.wallet.application.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import org.joda.money.CurrencyUnit;

import java.io.IOException;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */
class CurrencyUnitMarshaller {

    private CurrencyUnitMarshaller() {
    }

    public static class CurrencyUnitSerializer extends JsonSerializer<CurrencyUnit> {

        public static final CurrencyUnitSerializer INSTANCE = new CurrencyUnitSerializer();

        private CurrencyUnitSerializer() {

        }

        @Override
        public void serialize(CurrencyUnit value, JsonGenerator jgen,
                              SerializerProvider provider) throws IOException {
            jgen.writeStartObject();
            jgen.writeStringField("countryCode", value.getCode());
            jgen.writeStringField("numericCode", value.getNumeric3Code());
            jgen.writeEndObject();
        }

    }

    public static class CurrencyUnitDeserializer extends JsonDeserializer<CurrencyUnit> {

        public static final CurrencyUnitDeserializer INSTANCE = new CurrencyUnitDeserializer();

        private CurrencyUnitDeserializer() {

        }

        @Override
        public CurrencyUnit deserialize(JsonParser jsonParser,
                                        DeserializationContext deserializationContext) throws IOException {
            JsonNode moneyTree = jsonParser.readValueAsTree();
            String currencyCode = moneyTree.get("currency").asText();
            CurrencyUnit currency = currencyCode == null ? CurrencyUnit.of("IRR") : CurrencyUnit.of(currencyCode);
            return currency;
        }
    }
}
