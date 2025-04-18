package com.project.serenity_mental_center.dao.custom.impl;

import com.project.serenity_mental_center.config.FactoryConfiguration;
import com.project.serenity_mental_center.entity.Patient;
import com.project.serenity_mental_center.entity.PatientProgram;
import com.project.serenity_mental_center.entity.PatientProgramId;
import com.project.serenity_mental_center.entity.TherapyProgram;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientProgramDAOImpl {
    FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    public ArrayList<PatientProgram> getAll() {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = null;
        List<PatientProgram> patientPrograms = null;
        try {
            transaction = session.beginTransaction();
            Query<PatientProgram> query = session.createQuery("FROM PatientProgram", PatientProgram.class); // Query for Patient entities
            patientPrograms = (List<PatientProgram>) query.list(); // Retrieve list of patients
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return (ArrayList<PatientProgram>) patientPrograms;
    }


    public boolean save(PatientProgramId patientProgramId, String patientId, String programId, Date registerDate) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Patient patient = session.get(Patient.class,patientId);
            TherapyProgram therapyProgram = session.get(TherapyProgram.class,programId);
            PatientProgram program = new PatientProgram(
                    patientProgramId,
                    patient,
                    therapyProgram,
                    registerDate
            );
            session.persist(program);
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

    public boolean update(PatientProgramId patientProgramId, String patientId, String programId, Date registerDate) {
        Session session = factoryConfiguration.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Patient patient = session.get(Patient.class,patientId);
            TherapyProgram therapyProgram = session.get(TherapyProgram.class,programId);
            PatientProgram program = new PatientProgram(
                    patientProgramId,
                    patient,
                    therapyProgram,
                    registerDate
            );
            session.merge(program);
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
