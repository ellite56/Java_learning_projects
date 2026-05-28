package exercise0;

public class Cat extends Animal{
    public Cat(String name,int age){
        super(name,age);
    }

    @Override
    public String toString() {
        return "Cat name = "+get_name()+", age = "+get_age();
    }
}
