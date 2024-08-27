/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.help.cityhelper.dao;

import com.help.cityhelper.entities.City;
import org.hibernate.query.Query;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author akshita
 */
public class CityDao {
    
    private SessionFactory factory;

    public CityDao(SessionFactory factory) {
        this.factory = factory;
    }
    
    //gives list of city
    
    public List<City> getCity()
    {
        Session s = this.factory.openSession();
        Query query=s.createQuery("from City");
        List<City> list=query.list();
        s.close();
        return list;
    }
    
    //getting number of city
    public int getcCount()
    {
        int f=0;
        Session session=null;
        try{
            String query="SELECT COUNT(c) FROM City c";
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
    
    // saving city
    
    public int saveCity(City city)
    {
        Session session = null;
    Transaction tx = null;
    int cityId = 0;
    try {
        session = this.factory.openSession();
        tx = session.beginTransaction();
        cityId = (int) session.save(city);
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
    return cityId;
    }
    
    //for saving from dropdown
    
    public City getCityById(int cId)
    {
        City city=null;
        try{
            Session s=this.factory.openSession();
            city=s.get(City.class, cId);
            s.close();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return city;
    }
    
    //removing city
   public void removeCityById(int id) {
    Session session = null;
    Transaction transaction = null;
    try {
        session = factory.openSession(); // Initialize session
        transaction = session.beginTransaction();

        // delete 
        String query = "DELETE FROM City WHERE id = :cId";
        int result = session.createQuery(query)
                            .setParameter("cId", id)
                            .executeUpdate();

        transaction.commit();
        
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
