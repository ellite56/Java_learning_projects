package exercise4;

import java.util.concurrent.TimeUnit;

public class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public String toString() {
        return "Cat name = " + getName() + ", age = " + getAge();
    }

    @Override
    public double goToWalk() {
        double walktime = getAge() * 0.25;
        long time_sleep = Math.round(walktime);
        try {
            TimeUnit.SECONDS.sleep(time_sleep);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return walktime;
    }
}
