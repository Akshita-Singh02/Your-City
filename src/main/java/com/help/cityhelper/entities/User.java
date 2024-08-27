/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.help.cityhelper.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="user")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uId;
    private String uName;
     @ManyToOne
    @JoinColumn(name = "city-id")
    private City city;
    private String uPassword;
    private int uMobile;
    private String uPic;
    private String uType;
    private String uEmail;
@Column(columnDefinition = "TEXT")  
    private String uBio;
    public String getuBio() {
        return uBio;
    }

    public void setuBio(String uBio) {
        this.uBio = uBio;
    }
    
    
    @OneToMany(mappedBy="user",cascade=CascadeType.ALL)
    private List<Post> posts=new ArrayList<>();

    public User(String uName, City city, String uPassword, int uMobile, String uPic, String uType, String uEmail,String uBio) {
        this.uName = uName;
        this.city = city;
        this.uPassword = uPassword;
        this.uMobile = uMobile;
        this.uPic = uPic;
        this.uType = uType;
        this.uEmail = uEmail;
        this.uBio = uBio;
    }

    public User() {
    }
    public User(int uId) {
        this.uId = uId;
    }

    public User(int uId, String uName, City city, String uPassword, int uMobile, String uPic, String uType, String uEmail,String uBio) {
        this.uId = uId;
        this.uName = uName;
        this.city = city;
        this.uPassword = uPassword;
        this.uMobile = uMobile;
        this.uPic = uPic;
        this.uType = uType;
        this.uEmail = uEmail;
        this.uBio = uBio;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

   

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    
    
    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

   

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public int getuMobile() {
        return uMobile;
    }

    public void setuMobile(int uMobile) {
        this.uMobile = uMobile;
    }

    public String getuPic() {
        return uPic;
    }

    public void setuPic(String uPic) {
        this.uPic = uPic;
    }

    public String getuType() {
        return uType;
    }

    public void setuType(String uType) {
        this.uType = uType;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }
    
    
    
    
    
    
    
    
}
