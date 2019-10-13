package com.task.strategies;

import java.util.Comparator;
import java.util.Map;

public class CustomComparator<K> implements Comparator<K> {
    private Map<K, Long> map;

    public CustomComparator(Map<K, Long> map) {
        this.map = map;
    }

    @Override
    public int compare(K a, K b) {
        return map.get(a).compareTo(map.get(b));
    }

}
