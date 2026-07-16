package repository;


import model.AnimalType;

import java.util.List;

public interface AnimalRepository {
    void updateSQL(AnimalType type, int stemmer);

    boolean voteForAnAnimal(int vote, AnimalType animal);
    List<AnimalType> getAllAnimals();
}