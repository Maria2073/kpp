package com.web.restservice;

import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;

@SpringBootTest
public class MainControllerTest {

    @Test
    public void Sum_2Plus5_7Returned()
    {
        Function function = new Function();
        Assert.assertEquals(7, function.add(2,5));
    }

    @Test
    public void Mul_2With5_10Returned()
    {
        Function function = new Function();
        Assert.assertEquals(10, function.mul(2,5));
    }
}
