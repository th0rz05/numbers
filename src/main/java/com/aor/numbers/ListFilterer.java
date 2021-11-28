package com.aor.numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListFilterer{
    public GenericListFilterer listFilter;

    public ListFilterer(GenericListFilterer filter) {
        listFilter = filter;
    }

    public List<Integer> filter(List<Integer> list) {
        List<Integer> filtered = new ArrayList<>();
        for(int i = 0;i<list.size();i++) {
            if (listFilter.accept(list.get(i))) {
                filtered.add(list.get(i));
            }
        }
        return filtered;
    }

}

