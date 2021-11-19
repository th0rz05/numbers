package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        ListDeduplicator deduplicator = new ListDeduplicator();
        int distinct = aggregator.distinct(list,deduplicator);
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

        class StubListDeduplicator implements GenericListDeduplicator{

            @Override
            public List<Integer> deduplicate(List<Integer> list, GenericListSorter listSorter) {
                return Arrays.asList(1,2,4);
            }
        }
        StubListDeduplicator deduplicator = new StubListDeduplicator();
        int distinct = aggregator.distinct(Arrays.asList(1, 2, 4, 2), deduplicator);
        Assertions.assertEquals(3,distinct);
    }
}
