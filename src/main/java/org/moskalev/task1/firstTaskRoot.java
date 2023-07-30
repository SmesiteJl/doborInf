package org.moskalev.task1;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class firstTaskRoot {


    public static boolean task1 (int[] arr) throws Exception {
        IntegerGenerator generator = new IntegerGenerator();
        List<Callable<Boolean>> taskList = new ArrayList<>();
        List<Boolean> answerList = new ArrayList<>();
        for (int i = 0; i < arr.length && generator.getValue() < arr.length; i++, generator.generateNext()) {
            int temp = generator.getValue();
            taskList.add(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    for (int j = 0; j < arr.length; j ++) {
                        if(arr[j] == arr[temp] && j != temp){
                            return true;
                        }
                    }
                    return false;
                }
            });

        }
        ExecutorService executor = Executors.newFixedThreadPool(arr.length);
        executor.invokeAll(taskList).stream().map(future -> {
            try{
                return future.get();

            }
            catch (Exception e){
                throw new IllegalStateException(e);
            }
        }).forEach(x -> answerList.add(x));
        executor.shutdown();
        return answerList.contains(true);
    }
}
