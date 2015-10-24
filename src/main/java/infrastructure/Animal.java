package infrastructure;

import java.util.List;

/**
 * Created by Paul on 24.10.2015.
 */
public interface Animal {

    boolean voteForAnAnimal(int vote, Animal animal);
    List<Animal> getAllAnimals();

}
