package ua.opnu;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Лабораторна робота 8: Узагальнене програмування\n");

        System.out.println("Завдання 1: MyOptional<T>");
        testMyOptional();

        System.out.println("\nЗавдання 2: BookData implements Comparable");
        testBookData();

        System.out.println("\nЗавдання 3: Printer.printArray()");
        testPrinter();

        System.out.println("\nЗавдання 4: Узагальнений метод filter()");
        testFilter();

        System.out.println("\nЗавдання 5: Узагальнений метод contains()");
        testContains();

        System.out.println("\nЗавдання 6: GenericTwoTuple та GenericThreeTuple");
        testTuples();

        System.out.println("\nДемонстрація роботи завершена.");
    }

    private static void testMyOptional() {
        System.out.println("Тест 1: Порожній MyOptional (наприклад, у користувача немає по-батькові)");
        MyOptional<String> middleName = new MyOptional<>();
        System.out.println("Результат: " + middleName);
        System.out.println("isPresent(): " + middleName.isPresent());
        System.out.println("orElse(\"немає\"): " + middleName.orElse("немає"));

        System.out.println("\nТест 2: Заповнений MyOptional (наприклад, логін користувача)");
        MyOptional<String> username = new MyOptional<>("admin");
        System.out.println("Результат: " + username);
        System.out.println("isPresent(): " + username.isPresent());
        System.out.println("get(): " + username.get());
        System.out.println("orElse(\"guest\"): " + username.orElse("guest"));

        System.out.println("\nТест 3: Виклик get() на порожньому об'єкті має кинути виняток");
        try {
            String test = middleName.get();
            System.out.println("Помилка: неочікуваний результат: " + test);
        } catch (IllegalStateException ex) {
            System.out.println("Очікуваний виняток отримано: " + ex.getMessage());
        }

        System.out.println("\nТест 4: Конструктор не повинен приймати null");
        try {
            MyOptional<String> broken = new MyOptional<>(null);
            System.out.println("Помилка: неочікуваний результат: " + broken);
        } catch (IllegalArgumentException ex) {
            System.out.println("Правильно: null не дозволено. Повідомлення: " + ex.getMessage());
        }
    }

    private static void testBookData() {
        BookData book1 = new BookData("Java Programming", "Author A", 10, 45.0);
        BookData book2 = new BookData("Python Basics", "Author B", 20, 80.0);
        BookData book3 = new BookData("Advanced Java", "Author C", 5, 22.5);

        System.out.println("Створені книги:");
        System.out.println("Книга 1: " + book1);
        System.out.println("Книга 2: " + book2);
        System.out.println("Книга 3: " + book3);

        System.out.println("\nПорівняння книг за допомогою compareTo():");
        int result12 = book1.compareTo(book2);
        int result21 = book2.compareTo(book1);
        int result13 = book1.compareTo(book3);

        System.out.println("Книга 1 порівняно з Книгою 2: " + result12 + " (книга з більшим рейтингом має менше значення)");
        System.out.println("Книга 2 порівняно з Книгою 1: " + result21 + " (книга з меншим рейтингом має більше значення)");
        System.out.println("Книга 1 порівняно з Книгою 3: " + result13 + " (однаковий рейтинг, порівнюємо за назвою)");
    }

    private static void testPrinter() {
        Printer myPrinter = new Printer();
        Integer[] intArray = {1, 2, 3};
        String[] stringArray = {"Hello", "World"};

        System.out.println("Вивід масиву цілих чисел за допомогою узагальненого методу printArray():");
        myPrinter.printArray(intArray);

        System.out.println("\nВивід масиву рядків за допомогою узагальненого методу printArray():");
        myPrinter.printArray(stringArray);
    }

    @FunctionalInterface
    interface Predicate<T> {
        boolean test(T t);
    }

    private static <T> T[] filter(T[] input, Predicate<T> p) {
        @SuppressWarnings("unchecked")
        T[] buffer = (T[]) new Object[input.length];

        int counter = 0;
        for (T element : input) {
            if (p.test(element)) {
                buffer[counter++] = element;
            }
        }

        @SuppressWarnings("unchecked")
        T[] result = (T[]) Array.newInstance(
                input.getClass().getComponentType(), counter
        );
        System.arraycopy(buffer, 0, result, 0, counter);
        return result;
    }

    private static void testFilter() {
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Вихідний масив чисел: " + Arrays.toString(numbers));

        Integer[] evenNumbers = filter(numbers, x -> x % 2 == 0);
        System.out.println("Парні числа: " + Arrays.toString(evenNumbers));

        Integer[] greaterThan5 = filter(numbers, x -> x > 5);
        System.out.println("Числа більше 5: " + Arrays.toString(greaterThan5));

        String[] words = {"apple", "banana", "cherry", "date", "elderberry"};
        System.out.println("\nВихідний масив рядків: " + Arrays.toString(words));

        String[] longWords = filter(words, s -> s.length() > 5);
        System.out.println("Рядки довжиною більше 5 символів: " + Arrays.toString(longWords));
    }

    private static <T extends Comparable<T>, V extends T> boolean contains(T[] array, V element) {
        for (T item : array) {
            if (item.equals(element)) {
                return true;
            }
        }
        return false;
    }

    private static void testContains() {
        Integer[] intArray = {1, 2, 3, 4, 5};
        System.out.println("Масив цілих чисел: " + Arrays.toString(intArray));
        System.out.println("Містить число 3: " + contains(intArray, 3));
        System.out.println("Містить число 10: " + contains(intArray, 10));

        String[] stringArray = {"apple", "banana", "cherry"};
        System.out.println("\nМасив рядків: " + Arrays.toString(stringArray));
        System.out.println("Містить рядок \"banana\": " + contains(stringArray, "banana"));
        System.out.println("Містить рядок \"orange\": " + contains(stringArray, "orange"));
    }

    private static void testTuples() {
        System.out.println("Демонстрація роботи GenericTwoTuple:");
        GenericTwoTuple<String, Integer> nameAndAge = new GenericTwoTuple<>("Іван", 25);
        System.out.println("Кортеж (ім'я, вік): " + nameAndAge);
        System.out.println("Доступ до елементів: Ім'я = " + nameAndAge.first + ", Вік = " + nameAndAge.second);

        GenericTwoTuple<Double, String> priceAndCurrency = new GenericTwoTuple<>(99.99, "UAH");
        System.out.println("Кортеж (ціна, валюта): " + priceAndCurrency);

        System.out.println("\nДемонстрація роботи GenericThreeTuple:");
        GenericThreeTuple<String, Integer, Double> studentInfo = 
                new GenericThreeTuple<>("Петро Петрович", 20, 85.5);
        System.out.println("Кортеж (ім'я, вік, рейтинг): " + studentInfo);
        System.out.println("Доступ до елементів: Ім'я = " + studentInfo.getFirst()
                + ", Вік = " + studentInfo.getSecond()
                + ", Рейтинг = " + studentInfo.getThree());

        GenericThreeTuple<Integer, String, Boolean> productInfo = 
                new GenericThreeTuple<>(123, "Ноутбук", true);
        System.out.println("Кортеж (ID, назва, наявність): " + productInfo);
        System.out.println("Доступ до елементів: ID = " + productInfo.getFirst()
                + ", Назва = " + productInfo.getSecond()
                + ", В наявності = " + productInfo.getThree());
    }
}
