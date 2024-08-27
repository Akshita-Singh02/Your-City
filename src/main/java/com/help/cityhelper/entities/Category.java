/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.help.cityhelper.entities;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.*;

/**
 *
 * @author akshita
 */
@Entity
@Table(name = "category")
public class Category {
      @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(length=10,name="category_id")
    private int categoryId;
    @Column(length=500,name="category_title")
    private String categoryTitle;
   @OneToMany(mappedBy="category",cascade=CascadeType.ALL)
   private List<Post> posts=new ArrayList<>();

    public Category(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public Category() {
    }

    public Category(int categoryId, String categoryTitle) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
    
    
}
