package exercise5;

import java.util.List;

public class AnimalIterator implements  BaseIterator<Animal>{
    private List<Animal>animals;
    private int ind;

    public AnimalIterator(List<Animal>animals){
        this.animals=animals;
    }

    @Override
    public Animal next() {
        Animal temp=animals.get(ind);
        ind++;
        return temp;
    }

    @Override
    public boolean hasNext() {
        boolean flag=false;
        if(ind<animals.size()){
            flag=true;
        }
        return flag;
    }

    @Override
    public void reset() {
        ind=0;
    }
}
