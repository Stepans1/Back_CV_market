package com.example.BootApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "post_mandatory")
public class Post {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Must be not empty")
    @Size(min = 2,max = 100,message = "LIMIT !!!")
    @Column(name = "post_header")
    private String post_header;

    @NotEmpty(message = "Must be not empty")
    @Size(min = 2,max = 100,message = "LIMIT !!!")
    @Column(name = "post_city")
    private String post_city;

    @NotEmpty(message ="Must be not empty" )
    @Column(name = "post_type")
    private String post_type;

    @NotNull(message = "Must be not empty")
    @Column(name = "posts_start_day")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date posts_start_day;

    @Column(name = "post_end_day",nullable = false)
    @NotNull(message = "Must be not empty")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date posts_end_day;

    @NotEmpty(message = "Must be not empty")
    @Column(name = "post_contact_phone")
    private String post_contactPhone;
    @NotEmpty(message = "Must be not empty")
    @Email(message = "Please provide correct email ")
    @Column(name = "post_email")
    private String post_email;
    @Column(name = "salary")
    @NotNull(message = "Must be not empty")
    private Integer salary;



    @NotEmpty(message = "Must be not empty")
    @Column(name = "company")
    private String company;

    @ManyToOne
    @JoinColumn(name = "person_id",referencedColumnName = "id",nullable = false)
    private Person owner;

    public Post(String post_header, String post_city,
                String post_type, Date posts_start_day, Date posts_end_day,
               String post_email,String post_contactPhone,Integer salary,String company) {
        this.post_header = post_header;
        this.post_city = post_city;
        this.post_type = post_type;
        this.posts_start_day = posts_start_day;
        this.posts_end_day = posts_end_day;
        this.post_email=post_email;
        this.post_contactPhone=post_contactPhone;
        this.salary=salary;
        this.company=company;

    }

    public Post() {


    }


    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }





    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPost_header() {
        return post_header;
    }

    public void setPost_header(String post_header) {
        this.post_header = post_header;
    }



    public String getPost_city() {
        return post_city;
    }

    public void setPost_city(String post_city) {
        this.post_city = post_city;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public Date getPosts_start_day() {
        return posts_start_day;
    }

    public void setPosts_start_day(Date posts_start_day) {
        this.posts_start_day = posts_start_day;
    }

    public Date getPosts_end_day() {
        return posts_end_day;
    }

    public void setPosts_end_day(Date posts_end_day) {
        this.posts_end_day = posts_end_day;
    }



    public String getPost_contactPhone() {
        return post_contactPhone;
    }

    public void setPost_contactPhone(String post_contactPhone) {
        this.post_contactPhone = post_contactPhone;
    }

    public String getPost_email() {
        return post_email;
    }

    public void setPost_email(String post_email) {
        this.post_email = post_email;
    }
}
