package com.project.serenity_mental_center;

import com.project.serenity_mental_center.config.FactoryConfiguration;
import com.project.serenity_mental_center.entity.Patient;
import com.project.serenity_mental_center.entity.TherapyProgram;
import com.project.serenity_mental_center.entity.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AppInitializer extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent load = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
        Scene scene = new Scene(load);
        stage.setScene(scene);
        stage.setTitle("Serenity Mental Health Center");
        stage.show();
    }

    public void initializeDatabase(){
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        transaction.commit();
        session.close();
    }
    public static void main(String[] args) {
        new AppInitializer().initializeDatabase();
        launch(args);
    }
}
