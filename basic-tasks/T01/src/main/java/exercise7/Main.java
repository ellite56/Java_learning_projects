package exercise7;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        if (!scanner.hasNextInt()) {
            System.out.printf("Input error");
        } else {
            int pos = 0;
            int next = 0;
            while (scanner.hasNext()) {
                if (!scanner.hasNextInt()){
                    break;
                }
                int cur = scanner.nextInt();
                if (cur < next) {
                    System.out.println("The sequence is not ordered from the ordinal number of the number " + pos);
                    return;
                }
                pos++;
                next = cur;
            }
            System.out.println("The sequence is ordered in ascending order");
        }
    }
}