package exercise1;

class Dog extends Animal {

    public Dog(String name,int age,double weight){
        super(name,age,weight);
    }

    @Override
    double getFeedInfoKg() {
        return getWeight()*0.3;
    }

    @Override
    public String toString() {
        return String.format(java.util.Locale.US,
                "Dog name = %s, age = %d, mass = %.2f, feed = %.2f",
                getName(), getAge(), getWeight(), getFeedInfoKg());
    }
}
