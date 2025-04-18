package com.project.serenity_mental_center.dao.custom.impl;

import com.project.serenity_mental_center.config.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class UserDAOImpl {
    private FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    public String getPassword(String userName) {
        Session session = null;
        String password = null;
        try {
            session = factoryConfiguration.getSession();
            Query query = session.createQuery("SELECT password FROM User WHERE username = :userName");
            query.setParameter("userName", userName);
            password = (String) query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return password;
    }
}
