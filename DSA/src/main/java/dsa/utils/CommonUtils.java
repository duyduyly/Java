package dsa.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CommonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final Random random = new Random();

    public static String parseJson(Object ob) {
        try {
            return objectMapper.writeValueAsString(ob);
        } catch (JsonProcessingException ignore) {
            return "ERROR";
        }
    }

    public static void countRuntime(String message, Runnable runnable) {
        long startTime = System.currentTimeMillis();
        System.out.println(message);
        System.out.println("Start time: " + startTime+ " ms");

        runnable.run();

        long endTime = System.currentTimeMillis();
        System.out.println("End time: " + endTime+ " ms");
        System.out.println("Total time: " + (endTime - startTime) + " ms");
        System.out.println("Total time: " + ((endTime - startTime)/1000) + " seconds");

        System.out.println();
    }

    public static int randomIntNum(int min, int max) {
        return  random.nextInt(max - min + 1) + min;
    }

    public static int[] randomIntArrayID(int size){
        int[] arr = new int[size];
        Set<Integer> set  = new HashSet<>();
        int index = 0;
        while(index < size){
            int randomValue = randomIntNum(0, 1000_000_000);
            if(!set.contains(randomValue)){
                arr[index] = randomValue;
                index++;
            }
            set.add(randomValue);
        }
        return arr;
    }

}
