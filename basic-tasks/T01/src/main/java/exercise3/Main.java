package exercise3;

import java.util.Scanner;

public class Main {
    public static boolean check_size(int size){
        boolean flag=true;
        if (size<=0){
            flag=false;
            System.out.println("Input error. Size <= 0");
        }
        return flag;
    }
    public static int input_size(){
        Scanner scanner = new Scanner(System.in);
        int size;
        while (true){
            try {
                size=Integer.parseInt(scanner.next());
                break;
            }
            catch (NumberFormatException e){
                System.out.println("Could not parse a number. Please, try again");
            }
        }
        return size;
    }
    public static boolean check_number(int arr[],int size){
        boolean flag=true;
        boolean negative=false;
        for (int i=0;i<size;i++){
            if (arr[i]< 0){
                negative=true;
            }
        }
        if (!negative){
            flag=false;
            System.out.println("There are no negative elements");
        }
        return flag;
    }
    public static int[] input_number(int size){
        int arr[]=new int[size];
        Scanner scanner = new Scanner(System.in);
        int number;
        for(int i=0;i<size;i++){
            while (true){
                try {
                    number=Integer.parseInt(scanner.next());
                    arr[i]=number;
                    break;
                }
                catch (NumberFormatException e){
                    System.out.println("Could not parse a number. Please, try again");
                }
            }
        }
        return arr;
    }

    public static void calc_output(int arr[],int size){
        double average=0;
        int count_negative=0;
        for (int i=0;i<size;i++){
            if (arr[i]<0){
                count_negative++;
                average+=arr[i];
            }
        }
        System.out.println(average/count_negative);
    }
    public static void main(String [] args){
        int size=0;
        int result[];
        size=input_size();
        if (check_size(size)){
            result=input_number(size);
            if (check_number(result,size)){
                calc_output(result,size);
            }
        }
    }
}
