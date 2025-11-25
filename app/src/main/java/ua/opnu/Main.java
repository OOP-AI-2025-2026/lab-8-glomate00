package ua.opnu;

import ua.opnu.Task1.MyOptional;
import ua.opnu.Task2.BookData;
import ua.opnu.Task3.Printer;
import ua.opnu.Task4.GenericFilter;
import ua.opnu.Task4.Predicate;
import ua.opnu.Task5.GenericContains;
import ua.opnu.Task6.GenericTwoTuple;
import ua.opnu.Task6.GenericThreeTuple;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== ЛАБОРАТОРНА РОБОТА 8: УЗАГАЛЬНЕНЕ ПРОГРАМУВАННЯ ===\n");
        
        demonstrateTask1();
        demonstrateTask2();
        demonstrateTask3();
        demonstrateTask4();
        demonstrateTask5();
        demonstrateTask6();
    }

    private static void demonstrateTask1() {
        System.out.println("=== ЗАВДАННЯ 1: MyOptional ===");
        

        MyOptional<String> middleName = new MyOptional<>();
        System.out.println(middleName);
        System.out.println("isPresent: " + middleName.isPresent());
        System.out.println("orElse: " + middleName.orElse("немає"));

        MyOptional<String> username = new MyOptional<>("admin");
        System.out.println(username);
        System.out.println("isPresent: " + username.isPresent());
        System.out.println("get(): " + username.get());
        System.out.println("orElse: " + username.orElse("guest"));

        try {
            middleName.get();
        } catch (IllegalStateException ex) {
            System.out.println("Очікуваний виняток: " + ex.getMessage());
        }

        try {
            new MyOptional<>(null);
        } catch (IllegalArgumentException ex) {
            System.out.println("Правильно не дозволив null: " + ex.getMessage());
        }
        
        System.out.println();
    }

    private static void demonstrateTask2() {
        System.out.println("=== ЗАВДАННЯ 2: BookData з Comparable ===");
        
        BookData[] books = {
            new BookData("Java Programming", "John Doe", 100, 450.0),
            new BookData("Python Basics", "Jane Smith", 80, 320.0),
            new BookData("Advanced Java", "Bob Johnson", 50, 225.0),
            new BookData("Web Development", "Alice Brown", 120, 480.0)
        };
        
        System.out.println("До сортування:");
        for (BookData book : books) {
            System.out.println(book);
        }
        
        Arrays.sort(books);
        
        System.out.println("\nПісля сортування:");
        for (BookData book : books) {
            System.out.println(book);
        }
        
        System.out.println();
    }

    private static void demonstrateTask3() {
        System.out.println("=== ЗАВДАННЯ 3: Узагальнений Printer ===");
        
        Printer myPrinter = new Printer();
        Integer[] intArray = {1, 2, 3};
        String[] stringArray = {"Hello", "World"};
        
        System.out.println("Друк Integer:");
        myPrinter.printArray(intArray);
        
        System.out.println("Друк String:");
        myPrinter.printArray(stringArray);
        
        System.out.println();
    }

    private static void demonstrateTask4() {
        System.out.println("=== ЗАВДАННЯ 4: Узагальнений filter ===");
        
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Predicate<Integer> evenPredicate = x -> x % 2 == 0;
        
        Integer[] evenNumbers = GenericFilter.filter(numbers, evenPredicate);
        System.out.println("Парні числа: " + Arrays.toString(evenNumbers));
        
        String[] words = {"apple", "banana", "cherry", "date", "elderberry"};
        Predicate<String> longWordsPredicate = word -> word.length() > 5;
        
        String[] longWords = GenericFilter.filter(words, longWordsPredicate);
        System.out.println("Довгі слова: " + Arrays.toString(longWords));
        
        System.out.println();
    }

    private static void demonstrateTask5() {
        System.out.println("=== ЗАВДАННЯ 5: Узагальнений contains ===");
        
        String[] fruits = {"apple", "banana", "cherry"};
        System.out.println("Масив: " + Arrays.toString(fruits));
        System.out.println("Містить 'banana': " + GenericContains.contains(fruits, "banana"));
        System.out.println("Містить 'orange': " + GenericContains.contains(fruits, "orange"));
        
        Integer[] numbers = {1, 3, 5, 7, 9};
        System.out.println("Масив: " + Arrays.toString(numbers));
        System.out.println("Містить 5: " + GenericContains.contains(numbers, 5));
        System.out.println("Містить 4: " + GenericContains.contains(numbers, 4));
        
        System.out.println();
    }

    private static void demonstrateTask6() {
        System.out.println("=== ЗАВДАННЯ 6: Узагальнені кортежі ===");
        
        GenericTwoTuple<String, Integer> studentRating = 
            new GenericTwoTuple<>("Іванов Петро", 95);
        System.out.println("Студент та рейтинг: " + studentRating);
        
        GenericTwoTuple<String, Double> productPrice = 
            new GenericTwoTuple<>("Ноутбук", 25000.50);
        System.out.println("Товар та ціна: " + productPrice);
        
        GenericThreeTuple<String, String, Integer> employeeInfo = 
            new GenericThreeTuple<>("Петренко Олександр", "Програміст", 5);
        System.out.println("Інформація про співробітника: " + employeeInfo);
        
        System.out.println("Ім'я студента: " + studentRating.first);
        System.out.println("Рейтинг студента: " + studentRating.second);
        System.out.println("Стаж співробітника: " + employeeInfo.third + " років");
        
        System.out.println();
    }
}
