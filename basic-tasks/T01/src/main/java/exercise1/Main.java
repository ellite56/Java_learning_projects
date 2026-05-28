package exercise1;

import java.util.Scanner;

public class Main {
    public static boolean check(int second) {
        boolean flag = true;
        if (second < 0) {
            flag = false;
        }
        return flag;
    }

    public static int input() {
        Scanner scanner = new Scanner(System.in);
        int second;
        while (true) {
            try {
                second = Integer.parseInt(scanner.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please, try again");
            }
        }
        return second;
    }

    public static int[] calculate(int second) {
        int[] result = new int[3];
        int hours = second / 3600;
        result[0] = hours;
        int minutes = (second % 3600) / 60;
        result[1] = minutes;
        int secondtime = second % 60;
        result[2] = secondtime;
        return result;
    }

    public static void output(int result[], boolean flag) {
        if (!flag) {
            System.out.println("Incorrect time");
        } else {
            System.out.printf("%02d:%02d:%02d", result[0], result[1], result[2]);
        }
    }

    public static void main(String[] args) {
        int second = input();
        int result[] = {0, 0, 0};
        if (check(second)) {
            result = calculate(second);
            output(result, true);
        } else {
            output(result, false);
        }
    }
}
