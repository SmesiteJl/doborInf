package org.moskalev.task1;

import org.junit.Assert;
import org.junit.Test;

import static org.moskalev.task1.firstTaskRoot.task1;

public class firstTaskRootTest extends Assert {
    @Test
    public void IsTrueTask1() throws Exception {
        assertEquals(true, task1(new int[] {1,2,3,1}));
    }

    @Test
    public void IsFalseask1() throws Exception {
        assertEquals(false, task1(new int[] {1,2,3,4}));
    }
    @Test
    public void IsTrueWithMoreNumsTask1() throws Exception {
        assertEquals(true, task1(new int[] {1,1,1,3,3,4,3,2,4,2}));
    }
}