package org.example.utils.dataClass;

import org.example.model.Person;
import org.example.utils.keys.KeyAge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class PersonAgeDataGroup {
    HashMap<KeyAge, Person[]> data = new HashMap<>();

    public void addPerson(Person person) {
        int age = person.getAge();
        KeyAge key = new KeyAge(age);
        if (data.containsKey(key)) {
            Person[] ageData = data.get(key);
            int lastElement = Arrays.stream(ageData)
                    .takeWhile(Objects::nonNull)
                    .toArray().length;
            if (lastElement < ageData.length) {
                ageData[lastElement] = person;
            } else {
                Person[] newAgeData = new Person[ageData.length * 2];
                System.arraycopy(ageData, 0, newAgeData, 0, ageData.length);
                newAgeData[lastElement] = person;
                data.put(new KeyAge(person.getAge()), newAgeData);
            }
        } else {
            Person[] ageData = new Person[100];  // Начальный размер массива для новой группы
            ageData[0] = person;
            data.put(new KeyAge(person.getAge()), ageData);
        }
    }

    public Person[] getPersons(int age) {
        KeyAge key = new KeyAge(age);
        if (data.containsKey(key)) {
            return data.get(key);
        } else {
            System.out.println("Такой группы не существует");
            throw new NullPointerException();
        }
    }
}
