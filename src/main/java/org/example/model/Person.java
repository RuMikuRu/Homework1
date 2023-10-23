package org.example.model;

public class Person {
    private String family;
    private String name;
    private int age;
    private int group;
    private int physics = 0;
    private int mathematics = 0;
    private int rus = 0;
    private int literature = 0;
    private int geometry = 0;
    private int informatics = 0;

    public Person(String family, String name, int age, int group, int physics, int mathematics, int rus, int literature, int geometry, int informatics) {
        this.family = family;
        this.name = name;
        this.age = age;
        this.group = group;
        this.physics = physics;
        this.mathematics = mathematics;
        this.rus = rus;
        this.literature = literature;
        this.geometry = geometry;
        this.informatics = informatics;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getPhysics() {
        return physics;
    }

    public void setPhysics(int physics) {
        this.physics = physics;
    }

    public int getMathematics() {
        return mathematics;
    }

    public void setMathematics(int mathematics) {
        this.mathematics = mathematics;
    }

    public int getRus() {
        return rus;
    }

    public void setRus(int rus) {
        this.rus = rus;
    }

    public int getLiterature() {
        return literature;
    }

    public void setLiterature(int literature) {
        this.literature = literature;
    }

    public int getGeometry() {
        return geometry;
    }

    public void setGeometry(int geometry) {
        this.geometry = geometry;
    }

    public int getInformatics() {
        return informatics;
    }

    public void setInformatics(int informatics) {
        this.informatics = informatics;
    }

    @Override
    public String toString() {
        return "Person{" +
                "family='" + family + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", group=" + group +
                ", physics=" + physics +
                ", mathematics=" + mathematics +
                ", rus=" + rus +
                ", literature=" + literature +
                ", geometry=" + geometry +
                ", informatics=" + informatics +
                '}';
    }
}
