package exercise5;

import java.util.Scanner;
import java.util.Set;

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

    public static boolean check_size(int size) {
        boolean flag = true;
        if (size <= 0) {
            flag = false;
            System.out.println("Input error. Size <= 0");
        }
        return flag;
    }

    public static double[] input_elem(int size) {
        Scanner scanner = new Scanner(System.in);
        double arr[] = new double[size];
        for (int i = 0; i < size; i++) {
            while (true) {
                try {
                    arr[i] = Double.parseDouble(scanner.next());
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Could not parse a number. Please, try again");
                }
            }
        }
        return arr;
    }

    public static double[] sort(double arr[], int size) {
        double min;
        int index;
        for (int i = 0; i < size; i++) {
            index=i;
            min=arr[i];
            for (int j = i; j < size; j++) {
                if (arr[j] < min) {
                    min = arr[j];
                    index=j;
                }
            }
            if (index!=i){
                double temp = arr[i];
                arr[i]=arr[index];
                arr[index]=temp;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int size = input_size();
        if (check_size(size)) {
            double arr[];
            arr = input_elem(size);
            arr = sort(arr, size);
            for (int i = 0; i < size; i++) {
                System.out.print(arr[i]+ " ");
            }
        }
    }
}
