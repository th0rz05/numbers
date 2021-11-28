package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PositiveFilterTest {
    PositiveFilter filter;
    @BeforeEach
    public void setup(){
        filter = new PositiveFilter();
    }
    @Test
    public void accept1(){
        boolean result = filter.accept(10);
        Assertions.assertEquals(true,result);
    }
    @Test
    public void accept2(){
        boolean result = filter.accept(1);
        Assertions.assertEquals(true,result);
    }
    @Test
    public void accept3(){
        boolean result = filter.accept(0);
        Assertions.assertEquals(false,result);
    }
    @Test
    public void accept4(){
        boolean result = filter.accept(-1);
        Assertions.assertEquals(false,result);
    }
    @Test
    public void accept5(){
        boolean result = filter.accept(-10);
        Assertions.assertEquals(false,result);
    }



}
