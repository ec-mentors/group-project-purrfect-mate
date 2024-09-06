package purrfectMate.data.dto;

import purrfectMate.data.Gender;

public class CatResponseDTO {

    private String name;
    private int age;
    private Gender gender;
    private String location;
    private String description;
    private String picture;  // Base64-encoded image

    public CatResponseDTO() {
    }

    public CatResponseDTO(String name, int age, Gender gender, String location, String description, String picture) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.location = location;
        this.description = description;
        this.picture = picture;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
