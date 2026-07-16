package infrastructure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FakeAnimalTest {

    @Test
    @DisplayName("voteForAnAnimal lagrer siste stemme og dyr i FakeAnimal")
    void voteForAnAnimal_lagrerData() {
        FakeAnimal fakeAnimal = new FakeAnimal();

        // vi bruker selve fakeAnimal som "animal"-objekt her, siden interface Animal er uten felter
        boolean result = fakeAnimal.voteForAnAnimal(3, fakeAnimal);

        assertTrue(result);
        assertEquals(3, fakeAnimal.sisteVote);
        assertEquals(fakeAnimal, fakeAnimal.sisteAnimal);

        List<Animal> alle = fakeAnimal.getAllAnimals();
        assertEquals(1, alle.size());
        assertEquals(fakeAnimal, alle.getFirst());
    }
}
