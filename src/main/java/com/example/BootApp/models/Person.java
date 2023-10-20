package com.example.BootApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "Person")
public class Person {



    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "must be not empty")
    @Size(min = 2,max = 30,message = "LIMIT !!!")
    @Column(name = "username")
    private String name;
    @Column(name = "password")
    @Size(min = 8,max = 30,message = "LIMIT !!!")
    private String password;

    @OneToMany(mappedBy = "owner"  ,fetch = FetchType.LAZY)
    private List<Post> posts;

    public Person( String name,String password) {
        this.password=password;
        this.name = name;
    }
    public Person(int id, String name,String password) {
        this.id=id;
        this.password=password;
        this.name = name;
    }
    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    public Person() {

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

    @Override
    public String toString() {
        return "Name"+getName()+"Password"+getPassword();
    }
}
