package com.haulmont.creditProcessor.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PayStrategyRegistryImpl implements PayStrategyRegistry<String, PayStrategy> {

    private final Map<String, PayStrategy> registry = new HashMap<>();

    @Override
    public void put(PayStrategy strategy) {
        registry.put(strategy.getName(), strategy);
    }

    @Override
    public PayStrategy get(String key) {
        return registry.get(key);
    }

    @Override
    public Set<String> getAllKeys() {
        return registry.keySet();
    }
}
