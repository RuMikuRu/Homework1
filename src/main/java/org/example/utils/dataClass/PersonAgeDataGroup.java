package org.example.model;

import org.example.utils.KeyAge;
import org.example.utils.KeyGroupNum;

import java.util.Arrays;
import java.util.HashMap;

public class PersonAgeDataGroup {
    HashMap<KeyAge, Person[]> data = new HashMap<>();

    public void addPerson(Person person) {
        int age = person.getAge();
        if (data.containsKey(age)) {
            Person[] ageData = data.get(age);
            int lastElement = (int) Arrays.stream(ageData).count();
            if (lastElement < ageData.length) {
                ageData[lastElement + 1] = person;
            } else {
                Person[] newAgeData = new Person[ageData.length * 2];
                System.arraycopy(ageData, 0, newAgeData, 0, ageData.length);
                newAgeData[lastElement+1] = person;
                data.put(new KeyAge(person.getAge()), newAgeData);
            }
        } else {
            Person[] ageData = new Person[100];  // Начальный размер массива для новой группы
            ageData[0] = person;
            data.put(new KeyAge(person.getAge()), ageData);
        }
    }

    public Person[] getPersons(int age) {
        if (data.containsKey(age)) {
            return data.get(age);
        } else {
            System.out.println("Такой группы не существует");
            throw new NullPointerException();
        }
    }
}
