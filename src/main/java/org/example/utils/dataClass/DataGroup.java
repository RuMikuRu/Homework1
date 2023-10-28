package org.example.utils.dataClass;

import org.example.model.Person;

import java.util.*;

public class DataGroup<T> {
    private final Map<T, List<Person>> groups;

    public DataGroup() {
        this.groups = new HashMap<>();
    }

    public void addPerson(Person person, T t) {
        groups.computeIfAbsent(t, k -> new ArrayList<>()).add(person);
    }

    public List<Person> getPersons(T t) {
        return groups.get(t);
    }
}
