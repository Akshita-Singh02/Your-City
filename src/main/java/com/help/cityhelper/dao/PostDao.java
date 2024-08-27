/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.help.cityhelper.dao;

import com.help.cityhelper.entities.Post;
import com.help.cityhelper.entities.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author akshita
 */
public class PostDao {
    
    private static SessionFactory factory;

   public PostDao(SessionFactory factory) {
        this.factory = factory;
    }
   
    public int savePost(Post p) {
    Session session = null;
    Transaction tx = null;
    int pId = 0;
    try {
        session = this.factory.openSession();
        tx = session.beginTransaction();
        pId = (int) session.save(p);
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
    return pId;
}
    
    public Boolean sPost(Post p) {
    Session session = null;
    Transaction tx = null;
    Boolean success = false;
    int pId = 0;

    try {
        session = this.factory.openSession();
        tx = session.beginTransaction();
        
        // Log the Post object before saving
        System.out.println("Saving Post: " + p);

        pId = (int) session.save(p);
        tx.commit();
        success = true;
        
        // Log the success message
        System.out.println("Post saved successfully with ID: " + pId);

    } catch (Exception e) {
        if (tx != null) {
            tx.rollback();
        }
        
        // Log the exception details
        System.err.println("Failed to save Post. Rolling back transaction.");
        e.printStackTrace();
        
        success = false;
    } finally {
        if (session != null) {
            session.close();
        }
    }
    return success;
}

    
     //getting number of category
    public int getpCount()
    {
        int f=0;
        Session session=null;
        try{
            String query="SELECT COUNT(p) FROM Post p";
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
  //removing post
   public void removePostById(int id) {
    Session session = null;
    Transaction transaction = null;
    try {
        session = factory.openSession(); // Initialize session
        transaction = session.beginTransaction();

       
        String query = "DELETE FROM Post WHERE id = :pId";
        int result = session.createQuery(query)
                            .setParameter("pId", id)
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
   
   //get user post count
   public int getuPostCount(int id)
    {
        int c = 0;
        Session session = null;
    try {
        String query = "SELECT COUNT(p.id) FROM Post p JOIN p.user u WHERE u.id = :uId";
        session = this.factory.openSession();
        Query<Long> q = session.createQuery(query, Long.class).setParameter("uId", id);
        Long count = q.getSingleResult();
        c = count.intValue();
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (session != null) {
            session.close();
        }
    }
    return c;
}
   
   //get all post
   public List<Post> getAllPost( )
   {
      List<Post> postList = null;
        Transaction transaction = null;
        try (Session session = this.factory.openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            
            // Create and execute query
            Query<Post> query = session.createQuery(" FROM Post ", Post.class);
//            query.setParameter("city-id", cityid);
            postList = query.list();
            
            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback the transaction on error
            }
            e.printStackTrace(); // Log the exception
        }
        return postList;
    }
   
   public List<Post> getAllPostById(int categoryId)
   {
      List<Post> postList = null;
        Transaction transaction = null;
        try (Session session = this.factory.openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            
            // Create and execute query
           String hql = "SELECT p FROM Post p JOIN p.category c WHERE c.categoryId = :categoryId ";
            Query<Post> query = session.createQuery(hql, Post.class);
            query.setParameter("categoryId", categoryId);
           // query.setParameter("city-id", cityid);
            
            postList = query.list();
            
            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback the transaction on error
            }
            e.printStackTrace(); // Log the exception
        }
        return postList;
    }
   
   //for user
   public List<Post> getAllPostByUserId(int uId)
   {
      List<Post> postList = null;
        Transaction transaction = null;
        try (Session session = this.factory.openSession()) {
            // Start a transaction
            transaction = session.beginTransaction();
            
            // Create and execute query
           String hql = "SELECT p FROM Post p JOIN p.user c WHERE c.uId = :uId";
            Query<Post> query = session.createQuery(hql, Post.class);
            query.setParameter("uId", uId);
            postList = query.list();
            
            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); // Rollback the transaction on error
            }
            e.printStackTrace(); // Log the exception
        }
        return postList;
    }
   
   
   public String getNameByPid(int pId) {
    String username = null;
    Transaction transaction = null;
    try (Session session = this.factory.openSession()) {
        // Start a transaction
        transaction = session.beginTransaction();
        
        // Create and execute query
        Query<Post> query = session.createQuery("FROM Post WHERE pId = :pId", Post.class);
        query.setParameter("pId", pId);
        Post post = query.uniqueResult();
        
        // Commit transaction
        transaction.commit();
        
        // Get the username from the Post object
        if (post != null) {
            username = post.getUser().getuName(); // Assuming the Post object has a getUser() method and User object has getUsername() method
        }
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback(); // Rollback the transaction on error
        }
        e.printStackTrace(); // Log the exception
    }
    return username;
}

public String getProfileByPid(int pId) {
    String pic = null;
    Transaction transaction = null;
    try (Session session = this.factory.openSession()) {
        // Start a transaction
        transaction = session.beginTransaction();
        
        // Create and execute query
        Query<Post> query = session.createQuery("FROM Post WHERE pId = :pId", Post.class);
        query.setParameter("pId", pId);
        Post post = query.uniqueResult(); 
        
        // Commit transaction
        transaction.commit();
        
        // Get the profile picture from the Post object
        if (post != null && post.getUser() != null) {
            pic = post.getUser().getuPic();
        }
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback(); // Rollback the transaction on error
        }
        // Use a logging framework instead of printStackTrace in production code
        e.printStackTrace(); 
    }
    return pic;
}

    
}
