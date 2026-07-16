package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Paul on 24.10.2015.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    int votes;
    AnimalType animalType;
}
