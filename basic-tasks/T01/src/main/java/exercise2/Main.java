package exercise2;

import java.util.Scanner;

public class Main {
    public static int input() {
        Scanner scanner = new Scanner(System.in);
        int fib = 0;
        while (true) {
            try {
                fib = Integer.parseInt(scanner.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please, try again");
            }
        }
        return fib;
    }

    public static int fibonachi(int fib) {
        if (fib == 0) {
            return 0;
        } else if (fib == 1) {
            return 1;
        }
        int result = fibonachi(fib - 1) + fibonachi(fib - 2);
        return result;
    }

    public static int check_overflow(int fib) {
        int flag = 1;
        if (fib > 46) {
            System.out.println("Too large n");
            flag = 0;
        }
        return flag;
    }

    public static int check_negative(int fib) {
        int flag = 1;
        if (fib < 0) {
            System.out.println("Incorrect number");
            flag = 0;
        }
        return flag;
    }

    public static void main(String[] arg) {
        int fib = input();
        int flag_over = check_overflow(fib);
        int flag_negative = check_negative(fib);
        if (flag_over == 1 && flag_negative == 1) {
            int result = fibonachi(fib);
            System.out.println(result);
        }
    }
}
