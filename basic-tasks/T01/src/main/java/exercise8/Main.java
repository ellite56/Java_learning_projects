package exercise8;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int input_size(Scanner scanner) {
        int size = 0;
        try {
            size = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Input error.");
        }
        return size;
    }

    public static boolean check_size(int size) {
        boolean flag = true;
        if (size <= 0) {
            System.out.println("Input error. Size <= 0");
            flag = false;
        }
        return flag;
    }

    public static List<String> input_string(Scanner scanner, int size) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(scanner.nextLine());
        }
        return list;
    }

    public static String input_substring(Scanner scanner) {
        String substring;
        substring = scanner.nextLine();
        return substring;
    }

    public static List<String> filter(List<String> str, String substring) {
        List<String> result = new ArrayList<>();
        for (String s : str) {
            if (s.contains(substring)) {
                result.add(s);
            }
        }
        return result;
    }

    public static void output(List<String> result) {
        for (int i = 0; i < result.size(); i++) {
            System.out.print(result.get(i));
            if (i < result.size() - 1) {
                System.out.print(", ");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = input_size(scanner);
        if (check_size(size)) {
            List<String> str = input_string(scanner, size);
            String substring = input_substring(scanner);
            List<String> result = filter(str, substring);
            output(result);
        }
    }
}
