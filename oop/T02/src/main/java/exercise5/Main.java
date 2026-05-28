package exercise5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static int input_size(Scanner scanner){
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
            flag=false;
            System.out.println("Incorrect input. Size <= 0");
        }
        return flag;
    }


    public static int input_age(Scanner scanner){
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
        return age;
    }
    public static void input_pet(Scanner scanner,int size,List<Animal>pets){
        int age;
        String type;
        String name;
        for(int i=0;i<size;i++){
            type= scanner.next();
            if(!"dog".equals(type) && !"cat".equals(type)){
                System.out.println("Incorrect input. Unsupported pet type");
                continue;
            }
            name=scanner.next();
            age=input_age(scanner);
            if(age<=0){
                System.out.println("Incorrect input. Age <= 0");
                continue;
            }
            pets.add(obj(type,name,age));

        }
    }

    public static Animal obj(String type,String name,int age){
        switch (type){
            case "dog":
                return new Dog(name,age);
            case "cat":
                return new Cat(name,age);
            default:
                return null;
        }
    }

    public static void output(List<Animal>pets){
        AnimalIterator iter =new AnimalIterator(pets);
        iter.reset();
        while (iter.hasNext()){
            System.out.println(iter.next());
        }
    }

    public static void main(String[] args){
        List<Animal>pets=new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        int size=input_size(scanner);
        if(check_size(size)){
            input_pet(scanner,size,pets);
            output(pets);
        }
    }
}
