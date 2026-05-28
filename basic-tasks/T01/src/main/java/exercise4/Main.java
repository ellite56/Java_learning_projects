package exercise4;

import java.util.Scanner;

public class Main {
    public static int input_size() {
        Scanner scanner = new Scanner(System.in);
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

    public static int[] input_number(int size) {
        int arr[] = new int[size];
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        while (i < size) {
            while (true) {
                try {
                    arr[i] = Integer.parseInt(scanner.next());
                    i += 1;
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Could not parse a number. Please, try again");
                }
            }
        }
        return arr;
    }

    public static boolean check_size(int size) {
        boolean flag = true;
        if (size <= 0) {
            flag = false;
            System.out.println("Input error. Size <= 0");
        }
        return flag;
    }

    public static boolean ckeck_equaly(int number) {
        boolean flag = false;
        int end, start;
        number = Math.abs(number);
        end = number % 10;
        int temp = number;
        while (temp >= 10) {
            temp /= 10;
        }
        start = temp;
        if (start == end) {
            flag = true;
        }
        return flag;
    }

    public static void calc(int arr[], int size) {
        boolean flag = false;
        int result[] = new int[size];
        int count = 0;
        int i = 0;
        while (i < size) {
            if (ckeck_equaly(arr[i])) {
                result[count] = arr[i];
                count++;
                flag = true;
            }
            i++;
        }
        if (!flag) {
            System.out.println("There are no such elements");
        } else {
            for (int j = 0; j < count; j++) {
                System.out.printf("%d ", result[j]);
            }
        }
    }

    public static void main(String[] args) {
        int size = input_size();
        if (check_size(size)) {
            int result[] = input_number(size);
            calc(result, size);
        }
    }
}