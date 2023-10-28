package org.example.ui;

import org.example.service.StudentService;
import org.example.ui.impl.CalculateAverageGradeCommand;
import org.example.ui.impl.FindHonorStudentsCommand;
import org.example.ui.impl.FindStudentsByLastNameCommand;

public class CommandBuilder {
    private final StudentService studentService;

    public CommandBuilder(StudentService studentService) {
        this.studentService = studentService;
    }

    public Command buildCommand(int commandName) {
        printMenu();
        return switch (commandName) {
            case 1 -> new CalculateAverageGradeCommand(studentService);
            case 2 -> new FindHonorStudentsCommand(studentService);
            case 3 -> new FindStudentsByLastNameCommand(studentService);
            //case 0 -> null;
            default -> throw new IllegalArgumentException("Invalid command: " + commandName);
        };
    }

    public void printMenu() {
        System.out.println("""
                Выбирете действие:
                1. Вывести средние оценки 10 и 11 классов\s
                2. Поиск всех отличников старше 14 лет\s
                3. Поиск ученика по фамилии\s
                0. Выход""");
    }
}
