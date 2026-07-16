package domain;

import model.AnimalType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import repository.FakeAnimalRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnimalVoteServiceTest {
    private AnimalVoteService animalVoteService;
    private FakeAnimalRepository fakeRepo;

    @BeforeEach
    void setup() {
        fakeRepo = new FakeAnimalRepository();
        animalVoteService = new AnimalVoteService(fakeRepo);
    }

    @Test
    @DisplayName("To stemmer for Grevling og én for Hare")
    void toGrevlingEnHare() {
        animalVoteService.registrerStemme(AnimalType.GREVLING);
        animalVoteService.registrerStemme(AnimalType.GREVLING);
        animalVoteService.registrerStemme(AnimalType.HARE);

        // sjekk intern logikk
        assertEquals(2, animalVoteService.getStemmerFor(AnimalType.GREVLING));
        assertEquals(1, animalVoteService.getStemmerFor(AnimalType.HARE));

        // sjekk at repository ble kalt med siste stemme (HARE, 1)
        assertEquals(AnimalType.HARE, fakeRepo.sisteType);
        assertEquals(1, fakeRepo.sisteStemmer);
    }
}
