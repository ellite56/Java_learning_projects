package exercise0;

public class Dog extends Animal{

    public Dog(String name,int age){
        super(name,age);
    }

    @Override
    public String toString() {
        return "Dog name = "+ get_name()+", age = "+ get_age();
    }
}
