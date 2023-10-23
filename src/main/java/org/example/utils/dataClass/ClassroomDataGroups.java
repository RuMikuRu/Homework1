package org.example.utils.dataClass;

import org.example.model.Person;
import org.example.utils.keys.KeyGroupNum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class ClassroomDataGroups {
    HashMap<KeyGroupNum, Person[]> data = new HashMap<>();

    public void addPerson(Person person) {
        int groupNum = person.getGroup();
        KeyGroupNum key = new KeyGroupNum(groupNum);
        if (data.containsKey(key)) {
            Person[] groupData = data.get(key);
            int lastElement = Arrays.stream(groupData)
                    .takeWhile(Objects::nonNull)
                    .toArray().length;
            if (lastElement < groupData.length) {
                groupData[lastElement] = person;
            } else {
                Person[] newGroupData = new Person[groupData.length * 2];
                System.arraycopy(groupData, 0, newGroupData, 0, groupData.length);
                newGroupData[lastElement] = person;
                data.put(new KeyGroupNum(person.getGroup()), newGroupData);
            }
        } else {
            Person[] groupData = new Person[100];  // Начальный размер массива для новой группы
            groupData[0] = person;
            data.put(new KeyGroupNum(person.getGroup()), groupData);
        }
    }

    public Person[] getPersons(int groupNum) {
        KeyGroupNum key = new KeyGroupNum(groupNum);
        if (data.containsKey(key)) {
            return data.get(key);
        } else {
            System.out.println("Такой группы не существует");
            throw new NullPointerException();
        }
    }
}
