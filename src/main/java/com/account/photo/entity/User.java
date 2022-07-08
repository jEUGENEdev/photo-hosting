package com.account.photo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Objects;

@Entity
public class User {
    @Id
    private long id;
    private String username, password, role;
    @ManyToMany(fetch= FetchType.EAGER)
    private List<Photo> photos;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && username.equals(user.username) && password.equals(user.password) && role.equals(user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role);
    }
}
