package com.aor.numbers;

import com.aor.numbers.ListSorter;

import java.util.ArrayList;
import java.util.List;

public interface GenericListDeduplicator {
    public List<Integer> deduplicate(List<Integer> list,GenericListSorter listSorter);
}
