package org.example.ui.impl;

import org.example.model.Person;
import org.example.service.StudentService;
import org.example.ui.Command;

import java.util.List;

public class FindHonorStudentsCommand implements Command {
    private final StudentService studentService;

    public FindHonorStudentsCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void execute() {
        List<Person> honorStudents = studentService.findHonorStudentsAboveAge(14);

        System.out.println("Honor students above age 14:");
        for (Person student : honorStudents) {
            System.out.println(student.toString());
        }
    }
}
