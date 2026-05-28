package exercise0;

abstract class Animal {
    private String name;
    private int age;

    public Animal(String name,int age){
        this.name=name;
        this.age=age;
    }

    public int get_age(){
        return age;
    }

    public String get_name(){
        return name;
    }
}
