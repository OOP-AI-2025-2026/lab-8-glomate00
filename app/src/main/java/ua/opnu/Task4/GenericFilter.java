package ua.opnu.Task4;

import java.util.Arrays;

public class GenericFilter {
    
    @SuppressWarnings("unchecked")
    public static <T> T[] filter(T[] input, Predicate<T> predicate) {
        // Створюємо масив того ж типу, що й вхідний
        T[] result = (T[]) java.lang.reflect.Array.newInstance(
            input.getClass().getComponentType(), input.length);
        
        int counter = 0;
        for (T element : input) {
            if (predicate.test(element)) {
                result[counter] = element;
                counter++;
            }
        }
        
        return Arrays.copyOfRange(result, 0, counter);
    }
}
