package org.example.utils.dataClass;

import org.example.model.Person;

@FunctionalInterface
public interface GroupCriterion<T> {
    T getKey(Person person);
}

