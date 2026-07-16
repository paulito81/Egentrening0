package domain;

import model.AnimalType;
import repository.AnimalRepository;

import java.util.*;

/**
 * Created by Paul on 10.09.15.
 */

public class AnimalVoteService {
    private final AnimalRepository animalRepository;
    private final Map<AnimalType, Integer> stemmerPerDyr = new EnumMap<>(AnimalType.class);

    public AnimalVoteService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
        prePopulateAnimalsList();
    }

    private void prePopulateAnimalsList() {
        stemmerPerDyr.put(AnimalType.GREVLING, 0);
        stemmerPerDyr.put(AnimalType.LEMMEN, 0);
        stemmerPerDyr.put(AnimalType.HARE, 0);
        stemmerPerDyr.put(AnimalType.ELG, 0);
        stemmerPerDyr.put(AnimalType.SILD, 0);
        stemmerPerDyr.put(AnimalType.MARKMUS, 0);
    }

    public void registrerStemme(AnimalType animalType) {
        int dagensStemmer = stemmerPerDyr.getOrDefault(animalType, 0);
        int nyeStemmer = dagensStemmer + 1;
        stemmerPerDyr.put(animalType, nyeStemmer);
        animalRepository.updateSQL(animalType, nyeStemmer);
    }

    public int getStemmerFor(AnimalType animalType) {
        return stemmerPerDyr.getOrDefault(animalType, 0);
    }
}