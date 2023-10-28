package org.example;

import org.example.service.StudentService;
import org.example.ui.Command;
import org.example.ui.CommandBuilder;
import org.example.utils.parser.CsvDataLoader;
import org.example.utils.parser.DataLoader;

import java.util.Scanner;


/*Выбрал HashMap так как у него поиск по критериям равен O(1), но вот с Person[] ничего лучшего не придумал,
Точнее как придумал, там расширяющие дерево надо использовать, но не придумал как всё это адаптировать
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataLoader dataLoader = new CsvDataLoader("/home/iliyatmh/IdeaProjects/Homework1/src/main/resources/students.csv");
        StudentService studentService = new StudentService(dataLoader);
        CommandBuilder commandBuilder = new CommandBuilder(studentService);
        commandBuilder.printMenu();
        int commandName = scanner.nextInt();
        while(commandName!=0){
            Command command = commandBuilder.buildCommand(commandName);
            command.execute();
            commandName = scanner.nextInt();
        }
    }

    public static void printMenu() {
        System.out.println("""
                Выбирете действие:
                1. Вывести средние оценки 10 и 11 классов\s
                2. Поиск всех отличников старше 14 лет\s
                3. Поиск ученика по фамилии\s
                0. Выход""");
    }
}
