/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.help.cityhelper.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author akshita
 */
public class FactoryProvider {
    
  private static SessionFactory factory;

    public static SessionFactory getFactory() {
         try {
            if (factory == null) {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:/comp/env");
                DataSource dataSource = (DataSource) envContext.lookup("jdbc/mycity");

                factory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .setProperty("hibernate.connection.datasource", "java:/comp/env/jdbc/mycity")
                        .buildSessionFactory();

                // Add shutdown hook to close the SessionFactory
                Runtime.getRuntime().addShutdownHook(new Thread(FactoryProvider::closeFactory));
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return factory;
    }

    public static void closeFactory() {
        if (factory != null) {
            factory.close();
        }
    }
}