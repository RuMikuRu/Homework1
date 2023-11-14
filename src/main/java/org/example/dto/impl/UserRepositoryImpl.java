package org.example.dto.impl;

import org.example.dto.UserRepository;
import org.example.dto.model.PersonGrade;
import org.example.model.Person;
import org.example.utils.db.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private Connection conn;


    @Override
    public void addPerson(Person person) {
        conn = new Connection();
        try {
            Long lastId = conn.createConnection().executeQuery("SELECT MAX(student_id) FROM students;")
                    .getLong("max") + 1;
            lastId += 1;

            conn.createConnection().execute("INSERT INTO public.Students (student_id,family, name, age, group_id)\n" +
                    "VALUES (" + lastId + ",'" + person.getFamily() + "', '" + person.getName() + "', " + person.getAge() + ", " + person.getGroup() + ");");

            conn.createConnection().execute("\n" +
                    "INSERT INTO public.Results (student_id, physics, mathematics, rus, literature, geometry, informatics)\n" +
                    "VALUES (" + lastId + ", " + person.getPhysics() + ", " + person.getMathematics() + ", " + person.getRus() + ", " + person.getLiterature() + ", " + person.getGeometry() + ", " + person.getInformatics() + ");");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.disconnect();
        }
    }

    @Override
    public Person getPersonById(Long id) {
        conn = new Connection();
        try {
            ResultSet resultSet = conn.createConnection().executeQuery("SELECT Students.student_id, Students.family, Students.name, Students.age, Students.group_id,\n" +
                    "Results.physics, Results.mathematics, Results.rus, Results.literature, Results.geometry, Results.informatics\n" +
                    "FROM Students\n" +
                    "INNER JOIN Results ON Students.student_id = Results.student_id\n" +
                    "WHERE Students.student_id = " + id + ";");
            return deSerialized(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.disconnect();
        }
    }

    @Override
    public void updatePersonById(Long id, Person newPerson) {
        conn = new Connection();
        try {
            conn.createConnection().execute("UPDATE Students\n" +
                    "   SET name = '" + newPerson.getName() + "', family = '" + newPerson.getFamily() + "'\n" +
                    " WHERE student_id = " + id + ";  \n" +
                    "\n" +
                    "UPDATE Results\n" +
                    "   SET informatics = " + newPerson.getInformatics() + ",rus = " + newPerson.getRus() + ",literature = " + newPerson.getLiterature() + ",mathematics = " + newPerson.getMathematics() + ", physics = " + newPerson.getPhysics() + ", informatics = " + newPerson.getInformatics() + "\n" +
                    " WHERE student_id = " + id + ";");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.disconnect();
        }
    }

    @Override
    public void deletePersonById(Long id) {
        try {
            conn.createConnection().execute("DELETE Students WHERE student_id = " + id + "DELETE Results WHERE studentd_id = " + id + "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            conn.disconnect();
        }
    }

    @Override
    public List<PersonGrade> averageGradeByGroup(int group) {
        conn = new Connection();
        try {
            List<PersonGrade> personGradeList = new ArrayList<>();
            ResultSet resultSet = conn.createConnection().executeQuery("SELECT \n" +
                    "    results.student_id,\n" +
                    "    students.family,\n" +
                    "    students.name,\n" +
                    "    AVG((results.physics + results.mathematics + results.rus + results.literature + results.geometry + results.informatics) / 6.0) OVER (PARTITION BY results.student_id) AS avg_cgrade\n" +
                    "FROM \n" +
                    "    students \n" +
                    "INNER JOIN \n" +
                    "    results on students.student_id = results.student_id \n" +
                    "WHERE \n" +
                    "    students.group_id = " + group + ";");

            while (resultSet.next()){
                PersonGrade personGrade = new PersonGrade(
                        resultSet.getLong("student_id"),
                        resultSet.getString("family"),
                        resultSet.getString("name"),
                        resultSet.getDouble("avg_grade")
                );
                personGradeList.add(personGrade);
            }
            return personGradeList;
        } catch (Exception e) {
            System.out.println("Ошибка к подключению базы данных :$e");
        } finally {
            conn.disconnect();
        }

        return null;
    }

    @Override
    public List<Person> getTopListByAge(int age) {
        conn = new Connection();
        List<Person> top = new ArrayList<>();
        try {
            ResultSet resultSet = conn.createConnection().executeQuery("SELECT \n" +
                    "  Students.student_id, \n" +
                    "  Students.family, \n" +
                    "  Students.name, \n" +
                    "  Students.age, \n" +
                    " Students.group_id, \n" +
                    "  results.physics, \n" +
                    "  results.mathematics, \n" +
                    "  results.rus, \n" +
                    "  results.literature, \n" +
                    "  results.geometry, \n" +
                    "  results.informatics \n" +
                    "FROM \n" +
                    "  public.Students \n" +
                    "INNER JOIN public.results ON Students.student_id = results.student_id\n" +
                    "WHERE \n" +
                    "  Students.age > " + age + " \n" +
                    "  AND \n" +
                    "  (results.physics + results.mathematics + results.rus + results.literature + results.geometry + results.informatics) / 6 >= 5;");
            while (resultSet.next()) {
                Person person = new Person(
                        resultSet.getLong("student_id"),
                        resultSet.getString("family"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getInt("group_id"),
                        resultSet.getInt("physics"),
                        resultSet.getInt("mathematics"),
                        resultSet.getInt("rus"),
                        resultSet.getInt("literature"),
                        resultSet.getInt("geometry"),
                        resultSet.getInt("informatics")
                );
                top.add(person);
            }
            return top;
        } catch (Exception e) {
            System.out.println("Ошибка к подключению базы данных :$e");
        } finally {
            conn.disconnect();
        }
        return null;
    }

    // FIXME: 08.11.2023 Исправить этот бред
    @Override
    public void getTopListByFamily(String family) {
        conn = new Connection();
        try {
            ResultSet resultSet = conn.createConnection().executeQuery("SELECT Students.family, Students.name, Groups.group_name, AVG((Results.physics + Results.mathematics + Results.rus + Results.literature + Results.geometry + Results.informatics) / 6) AS Average\n" +
                    "FROM public.Results\n" +
                    "INNER JOIN public.Students ON public.Results.student_id = public.Students.student_id\n" +
                    "INNER JOIN public.Groups ON public.Students.group_id = public.Groups.group_id\n" +
                    "WHERE Students.family = '" + family + "'\n" +
                    "GROUP BY Students.family, Students.name, Groups.group_name;");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("family" + " "
                        + resultSet.getString("name") + " "
                        + resultSet.getString("group_name") + " "
                        + resultSet.getDouble("averange")));
            }
        } catch (Exception e) {
            System.out.println("Ошибка к подключению базы данных :$e");
        } finally {
            conn.disconnect();
        }
    }

    @Override
    public Boolean updateGrade(Person person) {
        conn = new Connection();
        try {
            conn.createConnection().execute("UPDATE results\n" +
                    "SET physics = " + person.getPhysics() + ",\n" +
                    "rus = " + person.getRus() + ",\n" +
                    "literature = " + person.getLiterature() + ",\n" +
                    "mathematics = " + person.getMathematics() + ",\n" +
                    "geometry = " + person.getGeometry() + ",\n" +
                    "informatics = " + person.getInformatics() + ",\n" +
                    "WHERE student_id IN (\n" +
                    "  SELECT student_id\n" +
                    "  FROM students s\n" +
                    "  WHERE s.family = '" + person.getFamily() + "' \n" +
                    "  AND s.name = '" + person.getName() + "' \n" +
                    "  AND s.group_id = " + person.getGroup() + "\n" +
                    ");");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Person deSerialized(ResultSet resultSet) {
        Person person = null;
        try {
            while (resultSet.next()) {
                person = new Person(
                        resultSet.getLong("student_id"),
                        resultSet.getString("family"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getInt("group_id"),
                        resultSet.getInt("physics"),
                        resultSet.getInt("mathematics"),
                        resultSet.getInt("rus"),
                        resultSet.getInt("literature"),
                        resultSet.getInt("geometry"),
                        resultSet.getInt("informatics")
                );
            }
            return person;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
