package com.haulmont.creditProcessor.model;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public interface PayStrategyRegistry<K, V> {

    void put(PayStrategy strategy);

    V get(K key);

    Set<K> getAllKeys();
}
