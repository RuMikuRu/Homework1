package org.example.model;

import org.example.utils.KeyFirstFamily;

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
            Person[] firstFamilyData = new Person[100];  // Initial array size for new group
            firstFamilyData[0] = person;
            data.put(key, firstFamilyData);
        }
    }


    public Person[] getPersons(String firstFamily) {
        if (data.containsKey(firstFamily.charAt(0))) {
            return data.get(firstFamily.charAt(0));
        } else {
            System.out.println("Такой буквы не существует");
            throw new NullPointerException();
        }
    }
}
