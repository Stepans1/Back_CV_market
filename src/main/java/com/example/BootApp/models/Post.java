package com.example.BootApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Optional;

@Entity
@Table(name = "post")
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
    @Column(name = "post_body")
    private String post_body;
    @NotEmpty(message = "Must be not empty")
    @Size(min = 2,max = 100,message = "LIMIT !!!")
    @Column(name = "post_city")
    private String post_city;
    @NotEmpty(message = "Must be not empty")
    @Size(min = 2,max = 100,message = "LIMIT !!!")
    @Column(name = "post_type")
    private String post_type;


    @Column(name = "posts_start_day")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date posts_start_day;

    @Column(name = "post_end_day")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date posts_end_day;
    @NotEmpty(message = "Must be not empty")
    @Size(min = 2,max = 100,message = "LIMIT !!!")
    @Column(name = "post_requirements")
    private String post_requirements;
    @NotEmpty(message = "Must be not empty")
    @Size(min = 2,max = 100,message = "LIMIT !!!")
    @Column(name = "post_offer")
    private String post_offer;
    @Column(name = "post_contact_phone")
    private String post_contactPhone;
    @NotEmpty(message = "Must be not empty")
    @Email(message = "Please provide correct email ")
    @Column(name = "post_email")
    private String post_email;



    @ManyToOne
    @JoinColumn(name = "person_id",referencedColumnName = "id")
    private Person owner;

    public Post(String post_header, String post_body, String post_city,
                String post_type, Date posts_start_day, Date posts_end_day,
                String post_requirements, String post_offer,String post_email,String post_contactPhone) {
        this.post_header = post_header;
        this.post_body = post_body;
        this.post_city = post_city;
        this.post_type = post_type;
        this.posts_start_day = posts_start_day;
        this.posts_end_day = posts_end_day;
        this.post_requirements = post_requirements;
        this.post_offer = post_offer;
        this.post_email=post_email;
        this.post_contactPhone=post_contactPhone;

    }

    public Post() {


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

    public String getPost_body() {
        return post_body;
    }

    public void setPost_body(String post_body) {
        this.post_body = post_body;
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

    public String getPost_requirements() {
        return post_requirements;
    }

    public void setPost_requirements(String post_requirements) {
        this.post_requirements = post_requirements;
    }

    public String getPost_offer() {
        return post_offer;
    }

    public void setPost_offer(String post_offer) {
        this.post_offer = post_offer;
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
