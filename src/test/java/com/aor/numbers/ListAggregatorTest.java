package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class ListAggregatorTest {
    List<Integer> list;
    ListAggregator aggregator;

    @BeforeEach
    public void setup(){
     list = Arrays.asList(1,2,2,4,5);
     aggregator = new ListAggregator();
    }

    @Test
    public void sum() {

        int sum = aggregator.sum(list);
        Assertions.assertEquals(14, sum);
    }

    @Test
    public void max() {

        int max = aggregator.max(list);
        Assertions.assertEquals(5, max);
    }

    @Test
    public void min() {

        int min = aggregator.min(list);
        Assertions.assertEquals(1, min);
    }

    @Test
    public void distinct() {
        class StubListSorter implements GenericListSorter{

            @Override
            public List<Integer> sort(List<Integer> list) {
                return Arrays.asList(1,2,2,4,5);
            }
        }
        class StubListDeduplicator implements GenericListDeduplicator{

            @Override
            public List<Integer> deduplicate(List<Integer> list, GenericListSorter listSorter) {
                return Arrays.asList(1,2,4,5);
            }
        }
        StubListDeduplicator deduplicator = new StubListDeduplicator();
        StubListSorter listSorter = new StubListSorter();

        int distinct = aggregator.distinct(list,deduplicator,listSorter);
        Assertions.assertEquals(4, distinct);
    }

    @Test
    public void max_bug_7263(){
        list = Arrays.asList(-1,-4,-5);
        int max = aggregator.max(list);
        Assertions.assertEquals(-1,max);
    }

    @Test
    public void distinct_bug_8726(){
        list = Arrays.asList(1,2,4,2);
        GenericListSorter listSorter = Mockito.mock(GenericListSorter.class);
        Mockito.when(listSorter.sort(Mockito.anyList())).thenReturn(Arrays.asList(1, 2, 2, 4));
        GenericListDeduplicator deduplicator = Mockito.mock(GenericListDeduplicator.class);
        Mockito.when(deduplicator.deduplicate(Mockito.anyList(),Mockito.anyObject())).thenReturn(Arrays.asList(1, 2, 4));
        int distinct = aggregator.distinct(Arrays.asList(1, 2, 4, 2), deduplicator,listSorter);
        Assertions.assertEquals(3,distinct);
    }
}
