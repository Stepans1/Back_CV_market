package com.example.BootApp.DTO;

import com.example.BootApp.models.Person;
import com.example.BootApp.models.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;

public class AddPostDTO {




    private String post_header;


    private String post_city;


    private String post_type;



    private Date posts_start_day;



    private Date posts_end_day;


    private String post_contactPhone;

    private String post_email;

    private int salary;



    private String company;


    private Person owner;


    private HashMap<String,String> attributes;


//    private String title;
//
//
//    private String body;

    public AddPostDTO(String post_header, String post_city, String post_type, Date posts_start_day,
                      Date posts_end_day, String post_contactPhone, String post_email,
                      int salary, String company, Person owner, HashMap<String,String> attributes) {
        this.post_header = post_header;
        this.post_city = post_city;
        this.post_type = post_type;
        this.posts_start_day = posts_start_day;
        this.posts_end_day = posts_end_day;
        this.post_contactPhone = post_contactPhone;
        this.post_email = post_email;
        this.salary = salary;
        this.company = company;
        this.owner = owner;
//        this.title = title;
//        this.body = body;
        this.attributes=attributes;
    }


    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
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

//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getBody() {
//        return body;
//    }
//
//    public void setBody(String body) {
//        this.body = body;
//    }
}
