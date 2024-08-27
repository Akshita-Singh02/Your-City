/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.help.cityhelper.dao;

import com.help.cityhelper.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author akshita
 */
public class UserDao {
     private SessionFactory factory;

    public UserDao(SessionFactory factory) {
        this.factory = factory;
    }
    
    public User getUserByEmailAndPassword(String email,String password)
    {
         User user=null;
        try{
            
            String query="from User where uEmail =:e and uPassword =:p";
            Session session=this.factory.openSession();
            Query q=session.createQuery(query);
            q.setParameter("e",email);
            q.setParameter("p",password);
            user=(User)q.getSingleResult();
            
            session.close();
        
    }
        catch(Exception e)
        {
             e.printStackTrace();
        }
     
    return user;
}
    
    public int getuCount()
    {
        int c = 0;
        Session session = null;
    try {
        String query = "SELECT COUNT(u) FROM User u";
        session = this.factory.openSession();
        Query<Long> q = session.createQuery(query, Long.class);
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
    
     //removing category
   public void removeUserById(int id) {
    Session session = null;
    Transaction transaction = null;
    try {
        session = factory.openSession(); // Initialize session
        transaction = session.beginTransaction();

        // HQL delete query
        String query = "DELETE FROM User WHERE id = :uId";
        int result = session.createQuery(query)
                            .setParameter("uId", id)
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
   
   public String getBioByName(String name)
   {
       String bio = null;
       Session session=null;
       Transaction tx=null;
       try{
           session=this.factory.openSession();
           tx=session.beginTransaction();
           String hql="SELECT u.uBio FROM USER u WHERE u.name =:uName";
           Query<String> query = session.createQuery(hql, String.class);
        query.setParameter("uName", name);
        
        // Fetch single result
        bio = query.uniqueResult();
           tx.commit();           
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
       return bio;
   }

   public void sProfile(int uid,String pic)
   {
         Session session = null;
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            // Load the user by ID
            User user = session.get(User.class, uid);
            if (user != null) {
                // Update the profile picture
                user.setuPic(pic);
                session.update(user);
                tx.commit();
            } else {
                System.out.println("User not found with ID: " + uid);
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();}}
       
       
   }
   
   public void sBio(int uid,String bio)
   {
         Session session = null;
        Transaction tx = null;
        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            // Load the user by ID
            User user = session.get(User.class, uid);
            if (user != null) {
                // Update the profile picture
                user.setuBio(bio);
                session.update(user);
                tx.commit();
            } else {
                System.out.println("User not found with ID: " + uid);
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();}}
       
       
   }
   
   // get city id
   
//   public int getCityId(int uid)
//   {
//       int cid=0;
//       Session s=null;
//       Transaction tx=null;
//       try{
//           s=factory.openSession();
//           tx=s.beginTransaction();
//              Query<User> query = s.createQuery("FROM User WHERE uid = :uId", User.class);
//        query.setParameter("uId", uid);
//        User user= query.uniqueResult();
//        
//        // Commit transaction
//        tx.commit();
//        
//        // Get the username from the Post object
//        if (user != null) {
//            cid = user.getCity().getcId(); // Assuming the Post object has a getUser() method and User object has getUsername() method
//        }
//    } catch (Exception e) {
//        if (tx != null) {
//            tx.rollback(); // Rollback the transaction on error
//        }
//        e.printStackTrace(); // Log the exception
//    }
//       return cid;
//   }
   
   
   
}