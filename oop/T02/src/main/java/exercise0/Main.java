package exercise0;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static int input_size(Scanner scanner) {
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

    public static boolean check_size(int size){
        boolean flag=true;
        if(size<=0){
            System.out.println("Incorrect input. Size <= 0");
            flag=false;
        }
        return flag;
    }

    public static List<Animal> input_category(Scanner scanner,int size){
        List<Animal> pets=new ArrayList<>();
        String type;
        for (int i=0;i<size;i++){
            type=scanner.next();
            if(!"dog".equals(type) && !"cat".equals(type)){
                System.out.println("Incorrect input. Unsupported pet type");
                continue;
            }
            String name=scanner.next();
            int age;
            while (true){
                try {
                    age=Integer.parseInt(scanner.next());
                    break;
                }
                catch (NumberFormatException e){
                    System.out.println("Could not parse a number. Please, try again");
                }

            }
            if (age<=0){
                System.out.println("Incorrect input. Age <= 0");
                continue;
            }
            switch (type){
                case "dog":
                    pets.add(new Dog(name,age));
                    break;
                case  "cat":
                    pets.add(new Cat(name,age));
                    break;
            }

        }
        return pets;

    }

    public static void output(List<Animal>pet){
        for (Animal p:pet){
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = input_size(scanner);
        if (check_size(size)){
            List<Animal> pets=input_category(scanner,size);
            output(pets);
        }
    }
}

