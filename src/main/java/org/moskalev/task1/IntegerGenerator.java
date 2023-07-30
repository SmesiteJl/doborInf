package org.moskalev.task1;

import lombok.Getter;

@Getter
public class IntegerGenerator {
    private Integer value;

    public IntegerGenerator(){
        this.value = 0;
    }
    public Integer generateNext(){
        value += 1;
        return value;
    }
    public Integer generateNextNTimes(Integer n){
        value += n;
        return value;
    }
    public Integer generatePrev(){
        value -=1;
        return value;
    }

    public Integer generatePrevNTimes(Integer n){
        value -= n;
        return value;
    }

    public void nullIt(){
        this.value = 0;
    }
}
