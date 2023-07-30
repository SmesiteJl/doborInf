package org.moskalev.task2;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {
    private String name;
    private Long age;
    private List<Dog> dogs;
}
