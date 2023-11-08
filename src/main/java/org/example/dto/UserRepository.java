package org.example.dto;

import org.example.model.Person;

import java.util.List;

public interface UserRepository {
    void addPerson(Person person);
    Person getPersonById(Long id);
    void updatePersonById(Long id,Person newPerson);
    void deletePersonById(Long id);
    Double averageGradeByGroup(int group);
    List<Person> getTopListByAge(int age);
    void getTopListByFamily(String Family);
}
