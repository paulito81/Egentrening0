package infrastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * Test-implementasjon av Animal som ikke snakker med database.
 */
public class FakeAnimal implements Animal {

    public int sisteVote;
    public Animal sisteAnimal;
    private final List<Animal> alle = new ArrayList<>();

    @Override
    public boolean voteForAnAnimal(int vote, Animal animal) {
        this.sisteVote = vote;
        this.sisteAnimal = animal;
        // legg til i lista for å kunne inspisere senere
        alle.add(animal);
        return true;
    }

    @Override
    public List<Animal> getAllAnimals() {
        return alle;
    }
}
