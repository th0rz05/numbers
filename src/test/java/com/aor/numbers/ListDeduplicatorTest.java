package com.aor.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ListDeduplicatorTest {
    List<Integer> list;
    List<Integer> expected;
    ListDeduplicator deduplicator;
    ListSorter listSorter;

    @BeforeEach
    public void setup(){
        expected = Arrays.asList(1,2,4,5);
        deduplicator = new ListDeduplicator();
    }
    @Test
    public void deduplicate() {
        list = Arrays.asList(1,2,4,2,5);
        class StubListSorter implements GenericListSorter{

            @Override
            public List<Integer> sort(List<Integer> list) {
                return Arrays.asList(1,2,2,4,5);
            }
        }
        StubListSorter listSorter2 = new StubListSorter();
        List<Integer> distinct = deduplicator.deduplicate(list,listSorter2);
        Assertions.assertEquals(expected, distinct);
    }

    @Test
    public void deduplicate_bug_8726(){
        list = Arrays.asList(1,2,4,2);
        class StubListSorter implements GenericListSorter{

            @Override
            public List<Integer> sort(List<Integer> list) {
                return Arrays.asList(1,2,2,4);
            }
        }
        StubListSorter stublistSorter = new StubListSorter();
        List<Integer> distinct = deduplicator.deduplicate(list,stublistSorter);
        Assertions.assertEquals(3,distinct.size());
    }
}
