package io.everyonecodes.backend.version0.data;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cats")
public class Cat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private String name;

    @Column()
    private String description;

    @Column()
    private String location;

    @ManyToOne
    @JoinColumn(name = "human_id", nullable = false)
    private Human human;

    public Cat(String name, String description, String location, Human human) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.human = human;
    }

    public Cat() {
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

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(id, cat.id) && Objects.equals(name, cat.name) && Objects.equals(description, cat.description) && Objects.equals(location, cat.location) && Objects.equals(human, cat.human);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, location, human);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", human=" + human +
                '}';
    }
}
