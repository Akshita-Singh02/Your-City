/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.help.cityhelper.entities;

import jakarta.persistence.*;
import java.util.*;
import org.hibernate.SessionFactory;

/**
 *
 * @author akshita
 */
@Entity
@Table(name="city")
public class City {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int cId;
    private int cZipcode;
    private String cName;
   @OneToMany(mappedBy="city",cascade=CascadeType.ALL)
   private List<User> users=new ArrayList<>();
   @OneToMany(mappedBy="city",cascade=CascadeType.ALL)
    private List<Post> posts=new ArrayList<>();

    public City() {
    }

    public City(int cZipcode, String cName) {
        this.cZipcode = cZipcode;
        this.cName = cName;
    }

    public City(int cId, int cZipcode, String cName) {
        this.cId = cId;
        this.cZipcode = cZipcode;
        this.cName = cName;
    }

   

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public int getcZipcode() {
        return cZipcode;
    }

    public void setcZipcode(int cZipcode) {
        this.cZipcode = cZipcode;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    
}
