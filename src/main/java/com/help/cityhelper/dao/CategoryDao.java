/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.help.cityhelper.dao;

import com.help.cityhelper.entities.Category;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author akshita
 */
public class CategoryDao {
    
    private  SessionFactory factory;

    public CategoryDao(SessionFactory factory) {
        this.factory = factory;
    }
    
    //for admin side
    public int saveCategory(Category cat) {
    Session session = null; 
    Transaction tx = null;
    int catId = 0;
    try {
        session = this.factory.openSession();
        tx = session.beginTransaction();
        catId = (int) session.save(cat);
        tx.commit();
    } catch (Exception e) {
        if (tx != null) {
            tx.rollback();
        }
        e.printStackTrace();
    } finally {
        if (session != null) {
            session.close();
        }
    }
    return catId;
}
    
     //getting number of category
    public int getcatCount()
    {
        int f=0;
        Session session=null;
        try{
            String query="SELECT COUNT(c) FROM Category c";
            session=this.factory.openSession();
            Query<Long> q = session.createQuery(query, Long.class);
        Long count = q.getSingleResult();
        f = count.intValue();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally {
        if (session != null) {
            session.close();
        }
    }
    return f;
    }
    
    //giving list of categories 
    public List<Category> getCategory()
    {
        Session s = this.factory.openSession();
        Query query=s.createQuery("from Category");
        List<Category> list=query.list();
        s.close();
        return list;
    }

    
    //for saving from dropdown
    
    public Category getCategoryById(int categoryId)
    {
        Category cat=null;
        try{
             Session s = this.factory.openSession();
             cat =s.get(Category.class,categoryId);
             s.close();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return cat;
    }
    
    //removing category
   public void removeCategoryById(int id) {
    Session session = null;
    Transaction transaction = null;
    try {
        session = factory.openSession(); // Initialize session
        transaction = session.beginTransaction();

        // HQL delete query
        String query = "DELETE FROM Category WHERE id = :categoryId";
        int result = session.createQuery(query)
                            .setParameter("categoryId", id)
                            .executeUpdate();

        transaction.commit();
        System.out.println("Number of entities deleted: " + result);
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    } finally {
        if (session != null) {
            session.close();
        }
    }
}

  
}
