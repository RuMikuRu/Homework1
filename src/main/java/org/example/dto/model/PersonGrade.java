package org.example.dto.model;

public class PersonGrade {
    private Long id;
    private String name;
    private String family;
    private Double avg;

    public PersonGrade(Long id,String name, String family, Double avg) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.avg = avg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
