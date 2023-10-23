package org.example.model;

import org.example.utils.KeyGroupNum;

import java.util.Arrays;
import java.util.HashMap;

public class ClassroomDataGroups {
    HashMap<KeyGroupNum, Person[]> data = new HashMap<>();

    public void addPerson(Person person) {
        int groupNum = person.getGroup();
        if (data.containsKey(groupNum)) {
            Person[] groupData = data.get(groupNum);
            int lastElement = (int) Arrays.stream(groupData).count();
            if (lastElement < groupData.length) {
                groupData[lastElement + 1] = person;
            } else {
                Person[] newGroupData = new Person[groupData.length * 2];
                System.arraycopy(groupData, 0, newGroupData, 0, groupData.length);
                newGroupData[lastElement+1] = person;
                data.put(new KeyGroupNum(person.getGroup()), newGroupData);
            }
        } else {
            Person[] groupData = new Person[100];  // Начальный размер массива для новой группы
            groupData[0] = person;
            data.put(new KeyGroupNum(person.getGroup()), groupData);
        }
    }

    public Person[] getPersons(int groupNum) {
        if (data.containsKey(groupNum)) {
            return data.get(groupNum);
        } else {
            System.out.println("Такой группы не существует");
            throw new NullPointerException();
        }
    }
}
