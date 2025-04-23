module com.project.serenity_mental_center {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires static lombok;
    requires java.naming;
    requires org.jetbrains.annotations;
    requires jbcrypt;
    requires net.sf.jasperreports.core;


    opens com.project.serenity_mental_center.controllers to javafx.fxml;
    opens com.project.serenity_mental_center.dto.tm to javafx.base;
    opens com.project.serenity_mental_center.config to jakarta.persistence;
    opens com.project.serenity_mental_center.entity to org.hibernate.orm.core;
    exports com.project.serenity_mental_center;
    opens com.project.serenity_mental_center.dto to javafx.base;
}