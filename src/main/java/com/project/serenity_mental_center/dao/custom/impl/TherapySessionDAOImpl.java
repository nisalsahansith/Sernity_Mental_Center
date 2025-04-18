package com.project.serenity_mental_center.dao.custom.impl;

import com.project.serenity_mental_center.config.FactoryConfiguration;
import com.project.serenity_mental_center.entity.Patient;
import com.project.serenity_mental_center.entity.TherapySession;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TherapySessionDAOImpl {
    private FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    public ArrayList<TherapySession> getAll() {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        List<TherapySession> therapySessions = null;

        try {
            transaction = session.beginTransaction();
            Query<TherapySession> query = session.createQuery("FROM TherapySession", TherapySession.class); // Query for Patient entities
            therapySessions = (List<TherapySession>) query.list(); // Retrieve list of patients
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (ArrayList<TherapySession>) therapySessions;
    }

    public String getLastId() {
        Session session = factoryConfiguration.getSession();
        String lastId = session.createQuery("SELECT TS.id FROM TherapySession TS ORDER BY TS.id DESC ", String.class)//String.class -return wena data type eka
                .setMaxResults(1)
                .uniqueResult();
        return lastId;
    }

    public TherapySession getAllById(String therapySessionId) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        TherapySession therapySession = null;

        try {
            transaction = session.beginTransaction();
            Query<TherapySession> query = session.createQuery("FROM TherapySession ts WHERE ts.id = :sessionId ", TherapySession.class);
            query.setParameter("sessionId",therapySessionId);
            therapySession = (TherapySession) query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return therapySession;
    }
}
