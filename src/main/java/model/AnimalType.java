package model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Paul on 10.09.15.
 */
public enum AnimalType {
    GREVLING(1),
    LEMMEN(2),
    HARE(3),
    ELG(4),
    SILD(5),
    MARKMUS(6);


    int index;

    AnimalType(int index) {

        this.index = index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    int getIndex() {

        return index;
    }

}