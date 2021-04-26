package com.web.restservice;

import com.web.restservice.entities.Cache;
import com.web.restservice.entities.Function;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;

@SpringBootTest
public class CacheTest {
    @Autowired
    private static Cache cache;

    @Test
    public void cachePutTest()
    {
        cache = new Cache();
        cache.putFunction(new Function(1,2, "addition"));
        cache.putFunction(new Function(5,6,"multiplication"));
        cache.putFunction(new Function());
        cache.putFunction(new Function());

        Assert.assertTrue(cache.isContain(new Function(1,2, "addition")));
        Assert.assertTrue(cache.isContain(new Function(5,6, "multiplication")));
        Assert.assertTrue(cache.isContain(new Function()));

        Assert.assertFalse(cache.isContain(new Function(5,7, "addition")));
        Assert.assertFalse(cache.isContain(new Function(0,0, "multiplication")));

    }

    @Test
    public void isContainsResultTest()
    {
        cache = new Cache();
        Assert.assertFalse(cache.isContain(new Function(1,2, "addition")));
        cache.putFunction(new Function(1,2, "addition"));
        Assert.assertTrue(cache.isContain(new Function(1,2, "addition")));

    }

}
