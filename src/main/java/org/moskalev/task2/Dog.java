package org.moskalev.task2;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class Dog {
    private String name;
    private Long age;
    private List<Long> ranks;
}
