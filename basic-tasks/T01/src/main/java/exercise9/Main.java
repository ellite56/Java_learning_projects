package exercise9;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int input_size(Scanner scanner) {
        int size;
        while (true) {
            try {
                size = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Could not parse a number. Please, try again");
            }
        }
        return size;
    }

    public static boolean check_size(int size) {
        boolean flag = true;
        if (size <= 0) {
            flag=false;
            System.out.println("Incorrect input.");
        }
        return flag;
    }

    public static List<User> input_people(Scanner scanner, int size){
        List<User>people=new ArrayList<>();
        String name;
        int age;
        while (people.size()!=size){
            name=scanner.nextLine();
            while (true) {
                try {
                    age = scanner.nextInt();
                    scanner.nextLine();
                    if (age <= 0) {
                        System.out.println("Incorrect input. Age <= 0");
                        break;
                    }
                    people.add(new User(name, age));
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Incorrect input.");
                    scanner.nextLine();
                }
            }
        }
        return people;
    }

    public static void output_check(List<User> people) {

        String result = people.stream()
                .filter(p -> p.get_age() >= 18)
                .map(p -> p.get_name())
                .collect(java.util.stream.Collectors.joining(", "));

        if (!result.isEmpty()) {
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = input_size(scanner);
        scanner.nextLine();
        if(check_size(size)){
            List<User>result=input_people(scanner,size);
            output_check(result);

        }
    }
}