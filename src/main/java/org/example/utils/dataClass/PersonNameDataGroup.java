package org.example.utils.dataClass;

import org.example.model.Person;
import org.example.utils.keys.KeyFirstFamily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class PersonNameDataGroup {
    HashMap<KeyFirstFamily, Person[]> data = new HashMap<>();

    public void addPerson(Person person) {
        char firstFamily = person.getFamily().charAt(0);
        KeyFirstFamily key = new KeyFirstFamily(firstFamily);

        if (data.containsKey(key)) {
            Person[] firstFamilyData = data.get(key);

            int lastElementIndex = Arrays.stream(firstFamilyData)
                    .takeWhile(Objects::nonNull)
                    .toArray().length;

            if (lastElementIndex < firstFamilyData.length) {
                firstFamilyData[lastElementIndex] = person;
            } else {
                Person[] newFirstFamilyData = new Person[firstFamilyData.length * 2];
                System.arraycopy(firstFamilyData, 0, newFirstFamilyData, 0, firstFamilyData.length);
                newFirstFamilyData[lastElementIndex] = person;
                data.put(key, newFirstFamilyData);
            }

        } else {
            Person[] firstFamilyData = new Person[100];  
            firstFamilyData[0] = person;
            data.put(key, firstFamilyData);
        }
    }


    public Person[] getPersons(char firstFamily) {
        KeyFirstFamily key = new KeyFirstFamily(firstFamily);
        if (data.containsKey(key)) {
            return data.get(key);
        } else {
            System.out.println("Такой буквы не существует");
            throw new NullPointerException();
        }
    }
}
