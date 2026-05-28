package exercise6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static String input_path() {
        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        return path;
    }

    public static boolean check_path(File file) {
        boolean flag = true;
        if (!file.exists()) {
            flag = false;
            System.out.println("Input error. File doesn't exist");
        }
        return flag;
    }

    public static boolean check_size(int size) {
        boolean flag = true;
        if (size <= 0) {
            flag = false;
            System.out.println("Input error. Size <= 0");
        }
        return flag;
    }

    public static int input_size(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext() && !scanner.hasNextInt()) {
            scanner.next();
        }
        int size = 0;
        if (scanner.hasNextInt()) {
            size = scanner.nextInt();
        }
        else {
            size=0;
        }
        scanner.close();
        return size;
    }

    public static void input_number(File file,int size) throws FileNotFoundException {
        Scanner scanner=new Scanner(file);
        scanner.useLocale(java.util.Locale.US);
        if (scanner.hasNext()){
            scanner.next();
        }
        double arr[] = new double[size];
        int count=0;
        while (scanner.hasNext() && count<size){
            if(scanner.hasNextDouble()){
                arr[count]=scanner.nextDouble();
                count++;
            }
            else {
                scanner.next();
            }
        }
        scanner.close();
        if(count<size){
            System.out.println("Input error. Insufficient number of elements");
        }
        else {
            calc_min_max(arr,count,size);
        }
    }
    public static void calc_min_max(double arr[],int size_arr,int size){
        double min=arr[0];
        double max=arr[0];
        for(int i=0;i<size_arr;i++){
            if (arr[i]<min){
                min=arr[i];
            }
            if(arr[i]>max){
                max=arr[i];
            }
        }
        output(arr,size_arr);
        write_file(min,max);

    }

    public static void output(double arr[],int size){
        System.out.println(size);
        for (int i=0;i<size;i++){
            System.out.print(arr[i]);
            if (i + 1 < size) {
                System.out.print(" ");
            }
        }
        System.out.println();

    }

    public static void write_file(double min,double max){
        File file=new File("src/main/java/exercise6/result.txt");
        try {
            PrintWriter printWriter=new PrintWriter(file);
            printWriter.println(min + " " + max);
            System.out.println("Saving min and max values in file");
            printWriter.close();
        }
        catch (FileNotFoundException e){
            System.out.println("Input error. File doesn't exist");
        }
    }

    public static void main(String[] args)  {
        String path = input_path();
        File file = new File(path);
        int size = 0;
        if (check_path(file)) {
            try {
                size=input_size(file);
            }
            catch (FileNotFoundException e){
                System.out.println("Input error. File doesn't exist");
                return;
            }
            if (check_size(size)) {
                try {
                    input_number(file,size);
                } catch (FileNotFoundException e) {
                    System.out.println("Input error. File doesn't exist");
                    return;
                }
            }
        }
    }
}
