package repository;

import model.AnimalType;

import java.util.ArrayList;
import java.util.List;

public class FakeAnimalRepository implements AnimalRepository {

    public AnimalType sisteType;
    public int sisteStemmer;
    public final List<AnimalType> alle = new ArrayList<>();

    @Override
    public void updateSQL(AnimalType type, int stemmer) {
        this.sisteType = type;
        this.sisteStemmer = stemmer;
    }

    @Override
    public boolean voteForAnAnimal(int vote, AnimalType animal) {
        this.sisteType = animal;
        this.sisteStemmer = vote;
        return true;
    }

    @Override
    public List<AnimalType> getAllAnimals() {
        return alle;
    }
}
