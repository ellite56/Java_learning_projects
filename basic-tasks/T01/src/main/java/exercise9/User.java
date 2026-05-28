package exercise9;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class User {
    private  String user_name;
    private  int age;

    public User(String name, int age) {
        this.user_name=name;
        this.age=age;
    }

    public  String get_name(){
        return user_name;
    }

    public  int get_age(){
        return age;
    }

    @Override
    public String toString() {
        return user_name;
    }
}
