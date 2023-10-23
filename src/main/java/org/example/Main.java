package org.example;

import org.example.service.PersonService;
import org.example.utils.dataClass.ClassroomDataGroups;
import org.example.model.Person;
import org.example.utils.dataClass.PersonAgeDataGroup;
import org.example.utils.dataClass.PersonNameDataGroup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


/*Выбрал HashMap так как у него поиск по критериям равен O(1), но вот с Person[] ничего лучшего не придумал,
Точнее как придумал, там расширяющие дерево надо использовать, но не придумал как всё это адаптировать
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ClassroomDataGroups classroomDataGroups = new ClassroomDataGroups();
        PersonAgeDataGroup personAgeDataGroups = new PersonAgeDataGroup();
        PersonNameDataGroup personNameDataGroup = new PersonNameDataGroup();

        try (BufferedReader br = new BufferedReader(new FileReader("/home/iliyatmh/IdeaProjects/Homework1/src/main/resources/students.csv"))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                String family = parts[0].trim();
                String name = parts[1].trim();
                int age = Integer.parseInt(parts[2].trim());
                int group = Integer.parseInt(parts[3].trim());
                int physics = Integer.parseInt(parts[4].trim());
                int mathematics = Integer.parseInt(parts[5].trim());
                int rus = Integer.parseInt(parts[6].trim());
                int literature = Integer.parseInt(parts[7].trim());
                int geometry = Integer.parseInt(parts[8].trim());
                int informatics = Integer.parseInt(parts[9].trim());

                Person person = new Person(
                        family,
                        name,
                        age,
                        group,
                        physics,
                        mathematics,
                        rus,
                        literature,
                        geometry,
                        informatics);

                classroomDataGroups.addPerson(person);
                personAgeDataGroups.addPerson(person);
                personNameDataGroup.addPerson(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        printMenu();
        int selectMenu = scanner.nextInt();
        while (selectMenu != 0) {
            switch (selectMenu) {
                case 1 -> {
                    Double averangeInGroup10 = new PersonService(classroomDataGroups.getPersons(10)).averangeInGroup();
                    Double averangeInGroup11 = new PersonService(classroomDataGroups.getPersons(11)).averangeInGroup();
                    System.out.println("Среднее значение для 10 классов " + averangeInGroup10 + ", а для 11 " + averangeInGroup11);
                }
                case 2 -> {
                    for (int i = 14; i < 18; i++) {
                        System.out.println(Arrays.toString(new PersonService(personAgeDataGroups.getPersons(i))
                                .searchTop().toArray()));
                    }
                }
                case 3 -> {
                    String family = scanner.next();
                    System.out.println(new PersonService(personNameDataGroup.getPersons(family.charAt(0)))
                            .searchPersonByFamily(family));
                }
                case 0 -> {
                    return;
                }
                default -> {printMenu();}
            }
            printMenu();
            selectMenu = scanner.nextInt();
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
