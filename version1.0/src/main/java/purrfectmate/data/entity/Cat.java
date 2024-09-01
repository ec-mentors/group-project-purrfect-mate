package purrfectmate.data.entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import purrfectmate.data.Gender;

import java.util.List;

@Entity
@Table(name = "cats")
@JsonPropertyOrder(value = { "id", "name", "age", "gender", "description", "location", "isUpForAdoption", "isNeutered", "isOutdoorCat", "human" })
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @Column()
    private int age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column()
    private String description;

    @Column()
    private String location;

    @Column()
    private boolean isUpForAdoption;

    @Column()
    private boolean isNeutered;

    @Column()
    private boolean isOutdoorCat;

    @Column()
    @ElementCollection(fetch = FetchType.EAGER)
    List<String> healthAttributes;


    @ManyToOne()
    @JoinColumn(name = "human_id")
    private Human human;


    public Cat(String name, Human human) {
        this.name = name;
        this.human = human;
    }



    public Cat() {
    }

    public Cat(Long id,
               String name,
               int age,
               Gender gender,
               String description,
               String location,
               boolean isUpForAdoption,
               boolean isNeutered,
               boolean isOutdoorCat,
               List<String> healthAttributes,
               Human human) {

        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.description = description;
        this.location = location;
        this.isUpForAdoption = isUpForAdoption;
        this.isNeutered = isNeutered;
        this.isOutdoorCat = isOutdoorCat;
        this.healthAttributes = healthAttributes;
        this.human = human;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isUpForAdoption() {
        return isUpForAdoption;
    }

    public void setUpForAdoption(boolean upForAdoption) {
        isUpForAdoption = upForAdoption;
    }

    public boolean isNeutered() {
        return isNeutered;
    }

    public void setNeutered(boolean neutered) {
        isNeutered = neutered;
    }

    public boolean isOutdoorCat() {
        return isOutdoorCat;
    }

    public void setOutdoorCat(boolean outdoorCat) {
        isOutdoorCat = outdoorCat;
    }

    public List<String> getHealthAttributes() {
        return healthAttributes;
    }

    public void setHealthAttributes(List<String> healthAttributes) {
        this.healthAttributes = healthAttributes;
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }
}
