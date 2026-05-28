package exercise3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    public static int input_size(Scanner scanner){
        int size = 0;
        try {
            size=Integer.parseInt(scanner.next());
        }
        catch (NumberFormatException e){
            System.out.println("Could not parse a number. Please, try again");
            return input_size(scanner);
        }
        return size;
    }

    public static boolean chek_size(int size){
        boolean flag=true;
        if(size<=0){
            System.out.println("Incorrect input. Size <= 0");
            flag=false;
        }
        return flag;
    }

    public static int input_age(Scanner scanner){
        int age;
        try {
            age=Integer.parseInt(scanner.next());
        }
        catch (NumberFormatException e){
            System.out.println("Could not parse a number. Please, try again");
            return input_age(scanner);
        }
        return age;
    }

    public static Animal input_one_pet(Scanner scanner){
        String type= scanner.next();
        if(!"dog".equals(type) && !"cat".equals(type)){
            System.out.println("Incorrect input. Unsupported pet type");
            return null;
        }
        String name=scanner.next();
        int age=input_age(scanner);
        if (age<=0){
            System.out.println("Incorrect input. Age <= 0");
            return null;
        }
        return add_list(type,name,age);
    }

    public static List<Animal> input_pets(Scanner scanner,int size){
        return IntStream.range(0,size).mapToObj(i->input_one_pet(scanner))
                .filter(Objects ::nonNull).toList();
    }

    public static Animal add_list(String type,String name,int age){
        switch (type){
            case "dog":
                return new Dog(name,age);
            case "cat":
                return new Cat(name,age);
            default:
                return null;
        }
    }

    public static List<Animal> add_one(List<Animal>pets){
        return pets.stream().map(p->{
            int new_age=(p.getAge()>10) ? (p.getAge()+1):p.getAge();
            if (p instanceof Dog) return new Dog(p.getName(),new_age);
            return new Cat(p.getName(),new_age);
        }).toList();
    }
    public static void output(List<Animal>pet){
        pet.stream().forEach(System.out::println);
    }

    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        int size=input_size(scanner);
        if(chek_size(size)){
            List<Animal>pets=input_pets(scanner,size);
            List<Animal>result=add_one(pets);
            output(result);

        }
    }
}
