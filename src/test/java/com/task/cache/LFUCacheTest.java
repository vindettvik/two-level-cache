package com.task.cache;

import com.task.strategies.LFUStrategy;

import com.task.strategies.StrategyType;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LFUCacheTest {
    private TwoLevelCache<Integer, String> cache;
    private final static String VALUE = "value";

    @After
    public void tearDown() throws Exception {
        cache.clear();
    }

    @Test
    public void replace() {
        cache = new TwoLevelCache<>(2,2, StrategyType.LFU);

        cache.put(0, VALUE);
        cache.get(0);
        cache.get(0); // freq 2
        cache.put(1, VALUE);
        cache.get(1);
        cache.get(1); // freq 2
        cache.put(2, VALUE);
        cache.get(2); // freq 1
        cache.put(3, VALUE);
        cache.get(3);
        cache.get(3); // freq 2

        assertTrue(cache.contains(0));
        assertTrue(cache.contains(1));
        assertTrue(cache.contains(2));
        assertTrue(cache.contains(3));

        cache.put(4, VALUE);
        cache.get(4);
        cache.get(4); // freq 2

        assertTrue(cache.contains(0));
        assertTrue(cache.contains(1));
        assertFalse(cache.contains(2)); // Freq 1, should be removed
        assertTrue(cache.contains(3));
        assertTrue(cache.contains(4));
    }
}
