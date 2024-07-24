package io.everyonecodes.backend.version1.data;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "humans")
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Use Long for numeric ID

    @Column(nullable = false)
    private String username;

    @Column()
    private String email;

    @Column()
    private String description;

    @Column()
    private String location;

    public Human(String username, String email, String description, String location) {
        this.username = username;
        this.email = email;
        this.description = description;
        this.location = location;
    }

    public Human() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        Human human = (Human) o;
        return Objects.equals(id, human.id) && Objects.equals(username, human.username) && Objects.equals(email, human.email) && Objects.equals(description, human.description) && Objects.equals(location, human.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, description, location);
    }

    @Override
    public String toString() {
        return "Human{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location +
                '}';
    }
}
