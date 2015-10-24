package model;

/**
 * Created by Paul on 10.09.15.
 */
public enum AnimalType {
    GREVLING(1), LEMMEN(2), HARE(3), ELG(4), SILD(5), MARKMUS(6) ;

    public void setIndex(int index) {
        this.index = index;
    }

    int index;

    AnimalType(int index) {

        this.index = index;
    }



    int getIndex() {

        return  index;
    }




}
/*
    public static AnimalType getType(int index) {
    AnimalType[] animalTypes = AnimalType.values();
    for (AnimalType animalType : animalTypes) {
        if(animalType.getIndex() == index)
            return animalType;
    }
    return null;
}
*/