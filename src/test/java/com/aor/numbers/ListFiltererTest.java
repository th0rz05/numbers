package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import sun.jvm.hotspot.types.CIntegerField;

import java.util.Arrays;
import java.util.List;

public class ListFiltererTest {
    List<Integer> list;
    List<Integer> result;
    @BeforeEach
    public void setup(){
        list = Arrays.asList(1,2,3,4,5,6,7);
        result = Arrays.asList();
    }
    @Test
    public void filterFalse(){
        GenericListFilterer filter_ = Mockito.mock(GenericListFilterer.class);
        Mockito.when(filter_.accept(Mockito.anyObject())).thenReturn(false);
        ListFilterer listfilter = new ListFilterer(filter_);
        result = listfilter.filter(list);
        Assertions.assertEquals(0,result.size());
    }
    @Test
    public void filterTrue(){
        GenericListFilterer filter_ = Mockito.mock(GenericListFilterer.class);
        Mockito.when(filter_.accept(Mockito.anyObject())).thenReturn(true);
        ListFilterer listfilter = new ListFilterer(filter_);
        result = listfilter.filter(list);
        Assertions.assertEquals(7,result.size());
    }
}
