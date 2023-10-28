package org.example.ui.impl;

import org.example.model.Person;
import org.example.service.StudentService;
import org.example.ui.Command;

import java.util.List;
import java.util.Scanner;

public class FindStudentsByLastNameCommand implements Command {
    private final StudentService studentService;

    public FindStudentsByLastNameCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        List<Person> students = studentService.findStudentsByLastName(lastName.charAt(0));

        System.out.println("Students with last name " + lastName + ":");
        for (Person student : students) {
            System.out.println(student.getFamily() + " " + student.getName());
        }
    }
}
