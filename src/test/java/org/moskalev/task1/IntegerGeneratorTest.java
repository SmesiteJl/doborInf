package org.moskalev.task1;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IntegerGeneratorTest extends Assert {

    @Test
    public void IntegerGeneratorTest() {
        IntegerGenerator integerGenerator = mock(IntegerGenerator.class);
        when(integerGenerator.generateNext()).thenReturn(5);
        assertEquals(5, Optional.ofNullable(integerGenerator.generateNext()));
    }


}