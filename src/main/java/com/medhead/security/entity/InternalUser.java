package com.medhead.security.entity;

import javax.persistence.*;

@Entity
@Table(name = "internalusers")
public class InternalUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    @ManyToOne
    @JoinColumn(name="role_id")
    private InternalRole role;

    public InternalUser(Integer id, String username, String password, InternalRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public InternalUser() {}

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

    public InternalRole getRole() {
        return role;
    }

    public void setRole(InternalRole role) {
        this.role = role;
    }

}
