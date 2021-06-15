package com.haulmont.creditProccesor;

import com.haulmont.creditProccesor.model.PayStrategyAnnuity;
import com.haulmont.creditProccesor.model.PayStrategyRegistry;
import com.haulmont.creditProccesor.model.PayStrategyRegistryImpl;
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
}
