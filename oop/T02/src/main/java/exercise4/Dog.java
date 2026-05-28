package exercise4;

import java.util.concurrent.TimeUnit;

public class Dog extends Animal {

    public Dog(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Dog name = " + getName() + ", age = " + getAge();
    }

    @Override
    public double goToWalk() {
        double walktime = getAge() * 0.5;
        long time_sleep =Math.round(walktime);
        try {
            TimeUnit.SECONDS.sleep(time_sleep);
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        return walktime;
    }
}
