package com.haulmont.creditProccesor;

import com.haulmont.creditProccesor.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public PayStrategyRegistry payStrategyRegistry() {
        PayStrategyRegistry payStrategyRegistry = new PayStrategyRegistryImpl();
        payStrategyRegistry.put(new PayStrategyAnnuity());
        return payStrategyRegistry;
    }
    @Bean
    public MoneyFactory moneyFactory(@Value("${money.currencyCode}") String currencyCode) {
        return new MoneyFactoryImpl(currencyCode);
    }
}
