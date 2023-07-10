package ir.snapppay.wallet.application.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */
class BigMoneyMarshaller {

    private BigMoneyMarshaller() {
    }

    public static class BigMoneySerializer extends JsonSerializer<BigMoney> {

        public static final BigMoneySerializer INSTANCE = new BigMoneySerializer();

        private BigMoneySerializer() {

        }

        @Override
        public void serialize(BigMoney value, JsonGenerator jgen,
                              SerializerProvider provider) throws IOException {
            jgen.writeStartObject();
            jgen.writeNumberField("amount", value.getAmount());
            jgen.writeStringField("currency", value.getCurrencyUnit().getSymbol());
            String pretty = prettyPrintWithCents(value);
            jgen.writeStringField("pretty", pretty);
            jgen.writeEndObject();
        }

        private String prettyPrintWithCents(BigMoney money) {
            return money.getAmount().toPlainString().concat(" ").concat(money.getCurrencyUnit().getSymbol());
        }
    }

    public static class BigMoneyDeserializer extends JsonDeserializer<BigMoney> {

        public static final BigMoneyDeserializer INSTANCE = new BigMoneyDeserializer();

        private BigMoneyDeserializer() {

        }

        @Override
        public BigMoney deserialize(JsonParser jsonParser,
                                    DeserializationContext deserializationContext) throws IOException {
            JsonNode moneyTree = jsonParser.readValueAsTree();
            double amount = moneyTree.get("amount").asDouble();
            JsonNode currencyNode = moneyTree.get("currency");
            CurrencyUnit currency = currencyNode == null ? CurrencyUnit.of("IRR")
                    : CurrencyUnit.of(currencyNode.asText());
            return BigMoney.of(currency, BigDecimal.valueOf(amount));
        }
    }
}
