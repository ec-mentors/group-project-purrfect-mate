package io.everyonecodes.backend.version0.data;


import com.fasterxml.jackson.annotation.JsonBackReference;
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



    // TODO: change human object reference to just the human id!!!!!
    @Column(name = "human_id", nullable = false)
    private Long humanId;



    public Cat(String name, Long humanId) {
        this.name = name;
        this.humanId = humanId;
    }

    public Long getHumanId() {
        return humanId;
    }

    public void setHumanId(Long humanId) {
        this.humanId = humanId;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(id, cat.id) && Objects.equals(name, cat.name) && Objects.equals(description, cat.description) && Objects.equals(location, cat.location) && Objects.equals(humanId, cat.humanId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, location, humanId);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", humanId=" + humanId +
                '}';
    }
}
