package org.example.ui.impl;

import org.example.service.StudentService;
import org.example.ui.Command;

public class CalculateAverageGradeCommand implements Command {
    private final StudentService studentService;

    public CalculateAverageGradeCommand(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void execute() {
//        double grade10 = studentService.calculateAverageGradeByClass(10);
//        double grade11 = studentService.calculateAverageGradeByClass(11);

//        System.out.println("Среднее значение оценок для 10 класса: " + grade10);
//        System.out.println("Среднее значение оценок для 10 класса: " + grade11);
    }
}

