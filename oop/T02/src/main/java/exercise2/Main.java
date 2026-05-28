package exercise2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static int input_size(Scanner scanner) {
        int size;
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
        int age;
        String name;
        String type;
        for (int i = 0; i < size; i++) {
            type = scanner.next();
            if (!"dog".equals(type) && !"cat".equals(type) && !"hamster".equals(type) &&
                    !"guinea".equals(type)) {
                System.out.println("Incorrect input. Unsupported pet type");
                continue;
            }
            name = scanner.next();
            age = input_age(scanner);
            if (age <= 0) {
                System.out.println("Incorrect input. Age <= 0");
                continue;
            }
            pets.add(add_list(type, name, age));
        }
    }

    public static Animal add_list(String type, String name, int age) {
        switch (type) {
            case "dog":
                return new Dog(name, age);
            case "cat":
                return new Cat(name, age);
            case "hamster":
                return new Hamster(name, age);
            case "guinea":
                return new GuineaPig(name, age);
            default:
                return null;
        }
    }

    public static void output(List<Animal> pets) {
        for (Animal p : pets) {
            if (p instanceof Herbivore) {
                System.out.println(p);
            }
        }
        for (Animal p : pets) {
            if (p instanceof Omnivore) {
                System.out.println(p);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Animal> pets = new ArrayList<>();
        int size = input_size(scanner);
        if (check_size(size)) {
            input_pets(scanner, size, pets);
            output(pets);

        }
    }
}
