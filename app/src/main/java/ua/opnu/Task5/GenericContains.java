package ua.opnu.Task5;

public class GenericContains {
    
    public static <T extends Comparable<T>, V extends T> boolean contains(T[] array, V element) {
        for (T item : array) {
            if (item != null && item.compareTo(element) == 0) {
                return true;
            }
        }
        return false;
    }
}
