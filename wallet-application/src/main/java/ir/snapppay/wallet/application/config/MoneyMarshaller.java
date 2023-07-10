package ir.snapppay.wallet.application.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */
class MoneyMarshaller {


    private MoneyMarshaller() {
    }

    public static class MoneySerializer extends JsonSerializer<Money> {

        public static final MoneySerializer INSTANCE = new MoneySerializer();

        private MoneySerializer() {

        }

        @Override
        public void serialize(Money value, JsonGenerator jgen,
                              SerializerProvider provider) throws IOException {
            jgen.writeStartObject();
            jgen.writeNumberField("amount", value.getAmount());
            jgen.writeStringField("currency", value.getCurrencyUnit().getSymbol());
            String pretty = prettyPrintWithCents(value);
            jgen.writeStringField("pretty", pretty);
            jgen.writeEndObject();
        }

        private String prettyPrintWithCents(Money money) {
            StringBuilder bld = new StringBuilder(money.getAmount().toPlainString());
            bld.append(" ");
            bld.append(money.getCurrencyUnit().getSymbol());
            return bld.toString();
        }
    }

    public static class MoneyDeserializer extends JsonDeserializer<Money> {

        public static final MoneyDeserializer INSTANCE = new MoneyDeserializer();

        private MoneyDeserializer() {

        }

        @Override
        public Money deserialize(JsonParser jsonParser,
                                 DeserializationContext deserializationContext) throws IOException {
            JsonNode moneyTree = jsonParser.readValueAsTree();
            double amount = moneyTree.get("amount").asDouble();
            JsonNode currencyNode = moneyTree.get("currency");
            CurrencyUnit currency = currencyNode == null ? CurrencyUnit.of("IRR")
                    : CurrencyUnit.of(currencyNode.asText());
            return Money.of(currency, BigDecimal.valueOf(amount));
        }
    }
}
