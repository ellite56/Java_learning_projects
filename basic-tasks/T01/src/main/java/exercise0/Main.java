package exercise0;

import java.util.Scanner;

public class Main {
    public static boolean check(double ab, double ac, double bc) {
        boolean flag = true;
        if (ab >= ac + bc) {
            flag = false;
        } else if (ac >= ab + bc) {
            flag = false;
        } else if (bc >= ab + ac) {
            flag = false;
        }
        return flag;
    }


    public static void main(String[] args) {
        double x1 = 0, y1 = 0, x2 = 0, y2 = 0, x3 = 0, y3 = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                x1 = Double.parseDouble(scanner.next());
                y1 = Double.parseDouble(scanner.next());
                x2 = Double.parseDouble(scanner.next());
                y2 = Double.parseDouble(scanner.next());
                x3 = Double.parseDouble(scanner.next());
                y3 = Double.parseDouble(scanner.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Could not parse a number. Please, try again");
            }
        }
        double ab = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double ac = Math.sqrt(Math.pow(x3 - x1, 2) + Math.pow(y3 - y1, 2));
        double bc = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));

        if (check(ab, ac, bc)) {
            double perimeter = ab + bc + ac;
            System.out.printf("Perimeter: %.3f", perimeter);

        } else {
            System.out.println("It's not a triangle");
        }
    }

}