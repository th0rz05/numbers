package com.aor.numbers;


public class DivisibleByFilter implements GenericListFilterer{
    public Integer number2;

    public DivisibleByFilter(Integer num){
        number2 = num;
    }
    public boolean accept(Integer number) {
        if(number%number2==0){
            return true;
        }
        return false;
    }
}
