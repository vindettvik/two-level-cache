package com.task.cache;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TwoLevelCacheTest {
    private TwoLevelCache<Integer, String> cache;
    private final static String VALUE = "value";

    @Before
    public void setUp() throws Exception {
        cache = new TwoLevelCache(1,1);
    }

    @After
    public void tearDown() throws Exception {
        cache.clear();
    }

    @Test
    public void putToFirst() throws Exception {
        cache.put(0, VALUE);
        assertTrue(cache.getFirstLevel().contains(0));
        assertFalse(cache.getSecondLevel().contains(0));
    }

    @Test
    public void putToSecond() throws Exception {
        cache.put(0, VALUE);
        cache.put(1, VALUE);
        assertTrue(cache.getFirstLevel().contains(0));
        assertFalse(cache.getFirstLevel().contains(1));
        assertTrue(cache.getSecondLevel().contains(1));
        assertFalse(cache.getSecondLevel().contains(0));
    }

    @Test
    public void contains() throws Exception {
        cache.put(0, VALUE);
        cache.put(1, VALUE);

        assertTrue(cache.contains(0));
        assertTrue(cache.contains(1));
    }

    @Test
    public void containsInLevels() throws Exception {
        cache.put(0, VALUE);
        cache.put(1, VALUE);

        assertTrue(cache.getFirstLevel().contains(0));
        assertTrue(cache.getSecondLevel().contains(1));
    }

    @Test
    public void remove() throws Exception {
        cache.put(0, VALUE);
        assertTrue(cache.contains(0));
        cache.remove(0);
        assertFalse(cache.contains(0));
    }

    @Test
    public void clear() throws Exception {
        cache.put(0, VALUE);
        cache.put(1, VALUE);
        cache.clear();
        assertFalse(cache.contains(0));
        assertFalse(cache.contains(1));
    }
}