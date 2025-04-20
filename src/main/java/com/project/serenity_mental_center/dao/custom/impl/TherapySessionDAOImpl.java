package com.project.serenity_mental_center.dao.custom.impl;

import com.project.serenity_mental_center.config.FactoryConfiguration;
import com.project.serenity_mental_center.entity.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
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

    public Object getAllByTherapistId(String therapistId) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        List<TherapySession> therapyPrograms = null;

        try {
            transaction = session.beginTransaction();
            Query<TherapySession> query = session.createQuery("FROM TherapySession tp WHERE tp.therapist.id = :therapistId", TherapySession.class); // Query for Patient entities
            query.setParameter("therapistId",therapistId);
            therapyPrograms = (List<TherapySession>) query.list(); // Retrieve list of patients
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (ArrayList<TherapySession>) therapyPrograms;
    }

    public boolean save(String id, Date date, Time startTime, Time endTime, String therapistId, String patientId, String therapyProgramID) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Patient patient = session.get(Patient.class,patientId);
            TherapyProgram therapyProgram = session.get(TherapyProgram.class,therapyProgramID);
            Therapist therapist = session.get(Therapist.class,therapistId);
            TherapySession therapySession = new TherapySession(
                    id,
                    date,
                    startTime,
                    endTime,
                    therapist,
                    patient,
                    therapyProgram
            );
            session.persist(therapySession);
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

    public boolean update(String id, String patientId, String therapistId, String therapyProgramID, Date date, Time startTime, Time endTime) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Patient patient = session.get(Patient.class,patientId);
            TherapyProgram therapyProgram = session.get(TherapyProgram.class,therapyProgramID);
            Therapist therapist = session.get(Therapist.class,therapistId);
            TherapySession therapySession = new TherapySession(
                    id,
                    date,
                    startTime,
                    endTime,
                    therapist,
                    patient,
                    therapyProgram
            );
            session.merge(therapySession);
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
