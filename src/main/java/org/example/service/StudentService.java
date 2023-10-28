package org.example.service;

import org.example.model.Person;
import org.example.utils.dataClass.DataGroup;
import org.example.utils.dataClass.GroupCriterion;
import org.example.utils.parser.DataLoader;

import java.util.List;

public class StudentService {
    private final DataGroup<Integer> studentsAgeDataGroup;
    private final DataGroup<Integer> studentsGroupDataGroup;
    private final DataGroup<Character> studentsNameDataGroup;

    public StudentService(DataLoader dataLoader) {
        GroupCriterion<Integer> ageCriterion = Person::getAge;
        GroupCriterion<Integer> groupCriterion = Person::getGroup;
        GroupCriterion<Character> nameCriterion = Person::getFirstFamily;
        this.studentsAgeDataGroup = new DataGroup<>();
        this.studentsGroupDataGroup = new DataGroup<>();
        this.studentsNameDataGroup = new DataGroup<>();

        List<Person> personList = dataLoader.load();
        for (Person person : personList) {
            this.studentsAgeDataGroup.addPerson(person, ageCriterion.getKey(person));
            this.studentsNameDataGroup.addPerson(person, nameCriterion.getKey(person));
            this.studentsGroupDataGroup.addPerson(person, groupCriterion.getKey(person));
        }
    }

    public double calculateAverageGradeByClass(int group) {
        List<Person> classStudents = studentsGroupDataGroup.getPersons(group);
        int totalGrade = classStudents.stream()
                .mapToInt(Person::getGrade)
                .sum();
        return classStudents.isEmpty() ? 0 : (double) totalGrade / classStudents.size();
    }

    public List<Person> findHonorStudentsAboveAge(int age) {
        return studentsAgeDataGroup.getPersons(age).stream()
                .filter(Person::getExcellent)
                .toList();
    }

    public List<Person> findStudentsByLastName(char firstChar) {
        return studentsNameDataGroup.getPersons(firstChar);
    }
}
