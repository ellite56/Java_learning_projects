package exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static int intput_size(Scanner scanner) {
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

    public static int age(Scanner scanner) {
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

    public static double mass(Scanner scanner) {
        double mass;
        while (true) {
            try {
                mass = Double.parseDouble(scanner.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please, try again");
            }
        }
        return mass;
    }

    public static List<Animal> input_category(Scanner scanner, int size,List<Animal>pets) {
        String type;
        int age;
        double mass;
        String name;
        for (int i = 0; i < size; i++) {
            type = scanner.next();
            if (!"dog".equals(type) && !"cat".equals(type)) {
                System.out.println("Incorrect input. Unsupported pet type");
                continue;
            }
            name = scanner.next();
            age = age(scanner);
            if (age <= 0) {
                System.out.println("Incorrect input. Age <= 0");
                continue;
            }
            mass = mass(scanner);
            if (mass <= 0) {
                System.out.println("Incorrect input. Mass <= 0");
                continue;
            }
            pets.add(add_list(type, name, age, mass));
        }
        return pets;
    }

    public static Animal add_list(String type, String name, int age, double mass) {
        switch (type) {
            case "dog":
                return new  Dog(name, age, mass);
            case "cat":
                return new Cat(name, age, mass);
            default:
                return null;
        }
    }

    public static void output(List<Animal> pets) {
        for (Animal p : pets) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Animal>pets=new ArrayList<>();
        int size = intput_size(scanner);
        if (check_size(size)) {
            input_category(scanner, size,pets);
            output(pets);
        }
    }
}
