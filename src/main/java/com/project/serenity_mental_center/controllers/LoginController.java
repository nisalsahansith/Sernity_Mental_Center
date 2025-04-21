package com.project.serenity_mental_center.controllers;

import com.project.serenity_mental_center.bo.custom.impl.PatientBOImpl;
import com.project.serenity_mental_center.bo.custom.impl.UserBOImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController{

    @FXML
    private Pane bodyPane;

    @FXML
    private Button btnSignIn;

    @FXML
    private Label erroMsg;

    @FXML
    private PasswordField password;

    @FXML
    private AnchorPane pageName;

    @FXML
    private TextField username;

    private UserBOImpl userBO = new UserBOImpl();

    @FXML
    void navigateToHome(ActionEvent event) throws IOException {
        String userName = username.getText();
        String Password = password.getText();
        String pWord = userBO.getPassword(userName);
        if (pWord != null){
            if (pWord.equals(Password)){
                navigatePage(event,"/view/AdminHomePage.fxml","Admin Home Page");
            }else{
                erroMsg.setText("Password is incorrect");
            }
        }else{
            erroMsg.setText("UserName not found");
        }
        if (userName == null){
            erroMsg.setText("please enter valid user name");
        }

    }


    @FXML
    void navigateSignUpPage(MouseEvent event) {
        try {
            bodyPane.getChildren().clear();
            Parent signUpUI = FXMLLoader.load(getClass().getResource("/view/SignUpPage.fxml"));
            bodyPane.getChildren().add(signUpUI);
            bodyPane.setStyle("-fx-background-color: transparent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void navigatePage(ActionEvent event,String path, String name) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Parent homePageRoot = loader.load();
            Scene homeScene = new Scene(homePageRoot);
            homePageRoot.setEffect(null);

            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(homeScene);
            primaryStage.setTitle(name);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
