package org.example.service;

import org.example.dto.UserRepository;
import org.example.dto.impl.UserRepositoryImpl;
import org.example.dto.model.PersonGrade;
import org.example.model.Person;
import org.example.utils.dataClass.DataGroup;
import org.example.utils.dataClass.GroupCriterion;
import org.example.utils.parser.DataLoader;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;



public class StudentService {
//    private final DataGroup<Integer> studentsAgeDataGroup;
//    private final DataGroup<Integer> studentsGroupDataGroup;
//    private final DataGroup<Character> studentsNameDataGroup;

    private final UserRepository repository = new UserRepositoryImpl();

//    public StudentService(DataLoader dataLoader) {
//        GroupCriterion<Integer> ageCriterion = Person::getAge;
//        GroupCriterion<Integer> groupCriterion = Person::getGroup;
//        GroupCriterion<Character> nameCriterion = Person::getFirstFamily;
//        this.studentsAgeDataGroup = new DataGroup<>();
//        this.studentsGroupDataGroup = new DataGroup<>();
//        this.studentsNameDataGroup = new DataGroup<>();
//
//        List<Person> personList = new ArrayList<>();
//        for (Person person : personList) {
//            this.studentsAgeDataGroup.addPerson(person, ageCriterion.getKey(person));
//            this.studentsNameDataGroup.addPerson(person, nameCriterion.getKey(person));
//            this.studentsGroupDataGroup.addPerson(person, groupCriterion.getKey(person));
//        }
//    }

    public List<PersonGrade> calculateAverageGradeByClass(int group) {
        return repository.averageGradeByGroup(group);
    }

    public List<Person> findHonorStudentsAboveAge(int age) {
        return repository.getTopListByAge(age);
    }

    public void findStudentsByLastName(String family) {
        repository.getTopListByFamily(family);
    }

    public Boolean changePersonGrade(Person request) {
       return repository.updateGrade(request);
    }
}
