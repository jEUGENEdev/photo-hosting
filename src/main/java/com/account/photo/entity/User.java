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
    private long vkId, lastPhotoPost;
    private String username, password, role;
    @ManyToMany(fetch=FetchType.EAGER, mappedBy="users")
    private List<Photo> photos;
    private boolean enable;

    public User() {
    }

    public User(long vkId, String username, String password, String role, boolean enable) {
        this.vkId = vkId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.enable = enable;
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public long getVkId() {
        return vkId;
    }

    public void setVkId(long vkId) {
        this.vkId = vkId;
    }

    public long getLastPhotoPost() {
        return lastPhotoPost;
    }

    public void setLastPhotoPost(long lastPhotoPost) {
        this.lastPhotoPost = lastPhotoPost;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", photos=" + photos +
                '}';
    }
}
