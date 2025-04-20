package com.project.serenity_mental_center.dao.custom.impl;

import com.project.serenity_mental_center.config.FactoryConfiguration;
import com.project.serenity_mental_center.entity.Patient;
import com.project.serenity_mental_center.entity.Payment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    public Payment getAllById(String paymentId) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        Payment payment = null;

        try {
            transaction = session.beginTransaction();
            Query<Payment> query = session.createQuery("FROM Payment p WHERE p.id = :paymentId ", Payment.class);
            query.setParameter("paymentId",paymentId);
            payment =  query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return payment;
    }

    public boolean delete(String paymentId) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Payment payment = session.get(Payment.class, paymentId);
            if (payment !=null){
                session.remove(payment);
                transaction.commit();
                return true;
            }
            return false;
        }catch (Exception e) {
            transaction.rollback();
            return false;
        }finally {
            if (session != null){
                session.close();
            }
        }
    }
}
