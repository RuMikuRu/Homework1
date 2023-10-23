package org.example.service;

import org.example.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PersonService {
    Person[] data;

    public PersonService(Person[] person) {
        this.data = person;
    }

    public Double averangeInGroup() {
        Double result = (double) 0;
        int countNotNull = Arrays.stream(data)
                .takeWhile(Objects::nonNull)
                .toArray().length;
        for (int i = 0; i < countNotNull; i++) {
            result += data[i].getGeometry() + data[i].getInformatics() + data[i].getLiterature() +
                    data[i].getMathematics() + data[i].getPhysics() + data[i].getRus();
        }
        return result / countNotNull;
    }

    public List<Person> searchTop() {
        int countNotNull = Arrays.stream(data)
                .takeWhile(Objects::nonNull)
                .toArray().length;
        List<Person> topPersons = new ArrayList<>();
        for (int i = 0; i < countNotNull; i++) {
            if (topPersonByLessons(data[i])) {
                topPersons.add(data[i]);
            }
        }
        return topPersons;
    }

    private Boolean topPersonByLessons(Person person) {
        return person.getPhysics() == 5 && person.getMathematics() == 5 && person.getRus() == 5 &&
                person.getLiterature() == 5 && person.getGeometry() == 5 && person.getInformatics() == 5;
    }

    public Person searchPersonByFamily(String family) {
        int countNotNull = Arrays.stream(data)
                .takeWhile(Objects::nonNull)
                .toArray().length;
        for (int i = 0; i < countNotNull; i++) {
            if (data[i].getFamily().contains(family)) {
                return data[i];
            }
        }
        return null;
    }

}
