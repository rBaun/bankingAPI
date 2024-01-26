package com.rbaun.banking.model.user;

import com.rbaun.banking.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "roles")
public class UserRole extends BaseEntity {

    private String name;

    @ManyToMany
    private Set<UserInfo> users = new HashSet<>();

    public UserRole() {
    }

    public UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(Set<UserInfo> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "name='" + name + '\'' +
                '}';
    }
}
