package model;

import java.lang.reflect.Type;

/**
 * Created by Paul on 24.10.2015.
 */
public class Animal {

    int votes;
    Type animalType;

    public Animal(int votes, Type animalType) {
        this.votes = votes;
        this.animalType = animalType;
    }
    public Animal(){
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public Type getAnimalType() {
        return animalType;
    }

    public void setAnimalType(Type animalType) {
        this.animalType = animalType;
    }


}
