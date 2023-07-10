package ir.snapppay.wallet.application.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ir.snapppay.wallet.service.config.ImportWalletServiceModule;
import org.joda.money.BigMoney;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author mohammad hejazi - smohammadhejazii@gmail.com
 * @since 2023.07.10
 */


@Configuration
@ImportWalletServiceModule
@ComponentScan(basePackages = {"ir.snapppay.wallet.application"})
public class WalletApplicationConfig implements WebMvcConfigurer {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.addBasenames("classpath:common_exceptions", "classpath:cargo_message_description", "classpath:cargo_io_exception_message", "classpath:cargo_validation_message");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Override
    public void configureMessageConverters(final List<HttpMessageConverter<?>> converters) {
        converters.add(new StringHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter(initializeObjectMapper()));
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(new ResourceHttpMessageConverter());
    }


    @Bean
    public ObjectMapper initializeObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.registerModules(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        objectMapper.enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        SimpleModule module = new SimpleModule();
        module.addSerializer(CurrencyUnit.class, CurrencyUnitMarshaller.CurrencyUnitSerializer.INSTANCE);
        module.addDeserializer(CurrencyUnit.class, CurrencyUnitMarshaller.CurrencyUnitDeserializer.INSTANCE);
        module.addSerializer(Money.class, MoneyMarshaller.MoneySerializer.INSTANCE);
        module.addDeserializer(Money.class, MoneyMarshaller.MoneyDeserializer.INSTANCE);
        module.addDeserializer(BigMoney.class, BigMoneyMarshaller.BigMoneyDeserializer.INSTANCE);
        module.addSerializer(BigMoney.class, BigMoneyMarshaller.BigMoneySerializer.INSTANCE);
        objectMapper.registerModule(module);
        return objectMapper;
    }

}
