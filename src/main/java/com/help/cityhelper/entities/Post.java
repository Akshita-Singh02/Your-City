/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.help.cityhelper.entities;

import jakarta.persistence.*;

@Entity
@Table(name="post")
public class Post {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int pId;
    private String pPic;
    private String pContent;
  
    private String pName;
   
    private int helpful;
    @ManyToOne
    @JoinColumn(name="category-id")
    private Category category;
    
    @ManyToOne
    @JoinColumn(name="user-id")
    private User user;
 
     @ManyToOne
    @JoinColumn(name="city-id")
    private City city;
     
     

    public Post(int pId, String pPic, String pContent, String pName, int helpful, Category category, User user, City city) {
        this.pId = pId;
        this.pPic = pPic;
        this.pContent = pContent;
        this.pName = pName;
        this.helpful = helpful;
        this.category = category;
        this.user = user;
        this.city = city;
    }

    public Post(String pPic, String pContent, String pName, int helpful, Category category, User user, City city) {
        this.pPic = pPic;
        this.pContent = pContent;
        this.pName = pName;
        this.helpful = helpful;
        this.category = category;
        this.user = user;
        this.city = city;
    }

    public Post() {
    }
    
     

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
    
     
     
    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpPic() {
        return pPic;
    }

    public void setpPic(String pPic) {
        this.pPic = pPic;
    }

    public String getpContent() {
        return pContent;
    }

    public void setpContent(String pContent) {
        this.pContent = pContent;
    }

    

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    

    public int getHelpful() {
        return helpful;
    }

    public void setHelpful(int helpful) {
        this.helpful = helpful;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
     
    
    
    
}
