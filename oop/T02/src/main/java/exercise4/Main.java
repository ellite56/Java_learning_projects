package exercise4;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Main {

    public static int input_size(Scanner scanner) {
        int size = 0;
        while (true) {
            try {
                size = Integer.parseInt(scanner.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please, try again");
            }
        }
        return size;
    }

    public static boolean check_size(int size) {
        boolean flag = true;
        if (size <= 0) {
            flag = false;
            System.out.println("Incorrect input. Size <= 0");
        }
        return flag;
    }

    public static int input_age(Scanner scanner) {
        int age;
        while (true) {
            try {
                age = Integer.parseInt(scanner.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please, try again");
            }
        }
        return age;
    }

    public static void input_pets(Scanner scanner, int size, List<Animal> pets) {
        String type;
        String name;
        int age;
        for (int i = 0; i < size; i++) {
            type = scanner.next();
            if (!"dog".equals(type) && !"cat".equals(type)) {
                System.out.println("Incorrect input. Unsupported pet type");
                continue;
            }
            name = scanner.next();
            age = input_age(scanner);
            if (age <= 0) {
                System.out.println("Incorrect input. Age <= 0");
                continue;
            }
            pets.add(obj(type, name, age));
        }

    }

    public static Animal obj(String type, String name, int age) {
        switch (type) {
            case "dog":
                return new Dog(name, age);
            case "cat":
                return new Cat(name, age);
            default:
                return null;
        }
    }

     public static void thread_output(List<Animal> pets,long programstart) {
         CountDownLatch startLatch = new CountDownLatch(1);
         List<Thread> threads = new ArrayList<>();

         for (Animal pet : pets) {
             Thread t = new Thread(() -> {
                 try {
                     startLatch.await();
                 } catch (InterruptedException e) {
                     Thread.currentThread().interrupt();
                     return;
                 }

                 double start = (System.nanoTime() - programstart) / 1_000_000_000.0;

                 pet.goToWalk();

                 double end = (System.nanoTime() - programstart) / 1_000_000_000.0;

                 System.out.printf(Locale.US,
                         "%s, start time = %.2f, end time = %.2f%n",
                         pet, start, end);
             });

             threads.add(t);
             t.start();
         }

         startLatch.countDown();

         for (Thread t : threads) {
             try {
                 t.join();
             } catch (InterruptedException e) {
                 Thread.currentThread().interrupt();
                 break;
             }
         }
     }

    public static void main(String[] args) {
        long programstart = System.nanoTime();
        Scanner scanner = new Scanner(System.in);
        List<Animal> pets = new ArrayList<>();
        int size = input_size(scanner);
        if (check_size(size)) {
            input_pets(scanner, size, pets);
            thread_output(pets,programstart);
        }
    }
}
