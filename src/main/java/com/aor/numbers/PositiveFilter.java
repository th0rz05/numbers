package com.aor.numbers;

public class PositiveFilter implements GenericListFilterer{
    public boolean accept(Integer number) {
        if (number>0){
            return true;
        }
        else{
            return false;
        }
    }
}
