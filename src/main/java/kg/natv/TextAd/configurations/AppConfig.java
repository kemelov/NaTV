package kg.natv.TextAd.configurations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kg.natv.TextAd.mappers.AdMapper;
import kg.natv.TextAd.mappers.ChannelMapper;
import kg.natv.TextAd.mappers.DiscountMapper;
import kg.natv.TextAd.mappers.OrderDateMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "kg.natv.TextAd.controllers.exception.CustomExceptionHandler.java")
public class AppConfig {
    @Bean
    public AdMapper adMapper(){
        return AdMapper.INSTANCE;
    }

    @Bean
    public ChannelMapper channelMapper(){
        return ChannelMapper.INSTANCE;
    }

    @Bean
    public DiscountMapper discountMapper(){
        return DiscountMapper.INSTANCE;
    }
    @Bean
    public OrderDateMapper orderDateMapper(){
        return OrderDateMapper.INSTANCE;
    }
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
