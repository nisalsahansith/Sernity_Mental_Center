package com.project.serenity_mental_center.dao.custom.impl;

import com.project.serenity_mental_center.config.FactoryConfiguration;
import com.project.serenity_mental_center.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PaymentDAOImpl {
    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    public String getLastId() {
        Session session = factoryConfiguration.getSession();
        String lastId = session.createQuery("SELECT pp.id FROM Payment pp ORDER BY pp.id DESC ", String.class)//String.class -return wena data type eka
                .setMaxResults(1)
                .uniqueResult();
        return lastId;
    }

    public boolean save(Payment payment) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(payment);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            if(session != null){
                session.close();
            }
        }
    }

    public boolean update(Payment payment) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.merge(payment);
            transaction.commit();
            return true;
        }catch (Exception e){
            transaction.rollback();
            return false;
        }finally {
            if(session != null){
                session.close();
            }
        }
    }
}
