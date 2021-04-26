package com.web.restservice;

import com.web.restservice.entities.Function;
import org.junit.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.Test;

@SpringBootTest
public class MainControllerTest {

    @Test
    public void Sum_2Plus5_7Returned()
    {
        Function function = new Function(2,5);
        Assert.assertEquals(7, function.add());
    }

    @Test
    public void Mul_2With5_10Returned()
    {
        Function function = new Function(2,5);
        Assert.assertEquals(10, function.mul());
    }
}
