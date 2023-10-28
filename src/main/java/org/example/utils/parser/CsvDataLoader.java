package org.example.utils.parser;

import org.example.model.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvDataLoader implements DataLoader {
    private final String filename;

    public CsvDataLoader(String filename) {
        this.filename = filename;
    }
    @Override
    public List<Person> load() {
        List<Person> personList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))) {
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
                personList.add(person);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personList;
    }
}
