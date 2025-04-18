package com.project.serenity_mental_center.controllers;

import com.project.serenity_mental_center.bo.custom.impl.UserBOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class UserManageController {
    @FXML
    private TextField TxtUserName;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnUpdate;

    @FXML
    private Pane userPane;

    @FXML
    private TableView<?> tableUser;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserId;

    UserBOImpl userBO = new UserBOImpl();

    @FXML
    void addProgram(ActionEvent event) {
        String userId = txtUserId.getText();
        String userName = txtName.getText();
        String password = txtPassword.getText();
        String email = txtEmail.getText();
    }

    @FXML
    void deleteProgram(ActionEvent event) {

    }

    @FXML
    void resetPage(ActionEvent event) {

    }

    @FXML
    void updateProgram(ActionEvent event) {

    }

}
