package exercise1;

public class Cat extends Animal {

    public Cat(String name,int age,double weight){
        super(name,age,weight);
    }

    @Override
    double getFeedInfoKg() {
        return getWeight()*0.1;
    }

    @Override
    public String toString() {
        return String.format(java.util.Locale.US,
                "Cat name = %s, age = %d, mass = %.2f, feed = %.2f",
                getName(), getAge(), getWeight(), getFeedInfoKg());
    }
}
