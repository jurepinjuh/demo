package com.jurepinjuh.demo.Models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
@Table(name="[USER]")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDUSER")
    private int id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "CONTACTNUMBER")
    private String contactNumber;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PASSWORD")
    private String password;
    @ManyToOne()
    @JoinColumn(name="ROLEID",referencedColumnName = "ID")
    private Role role;
    @Column(name = "USERNAME")
    private String username;
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public User(int id, String name, String surname, String contactNumber, String email, String password,String username) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.contactNumber = contactNumber;
        this.email = email;
        this.password = password;
        this.username=username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public User() {
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
