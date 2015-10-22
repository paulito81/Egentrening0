import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 10.09.15.
 */
public class AnimalVoteService {
    List<AnimalType> listOfAnimals;


    public AnimalVoteService(){
        listOfAnimals = new ArrayList<>();
        populateAnimalList();
        getListOfAnimals(listOfAnimals);

    }
    public void populateAnimalList(){

        listOfAnimals.add(AnimalType.GREVLING);
        listOfAnimals.add(AnimalType.LEMMEN);
        listOfAnimals.add(AnimalType.HARE);
        listOfAnimals.add(AnimalType.SILD);
        listOfAnimals.add(AnimalType.MARKMUS);
    }

    public void getListOfAnimals(List<AnimalType> animalTypes) {

        for(AnimalType animals : animalTypes){
            System.out.println(animals.index + ": \t\t" + animals.name() + " " );
        }

    }

}
