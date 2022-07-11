package com.account.photo.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Column(columnDefinition="text")
    private String name, fullName;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(joinColumns=@JoinColumn(name="photo_id"), inverseJoinColumns=@JoinColumn(name="user_id"))
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    private List<User> users;

    public Photo() {
    }

    public Photo(String name, String fullName) {
        this.name = name;
        this.fullName = fullName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return String.format("{\"id\": \"%s\", \"orig_name\": \"%s\", \"name\": \"%s\", \"like_count\": \"%s\"}",
                getId(), getName(), getFullName(), getUsers().size());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return id == photo.id && name.equals(photo.name) && fullName.equals(photo.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, fullName);
    }
}
