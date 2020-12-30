package org.company.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.company.App;
import org.company.model.Client;


import java.io.IOException;


public class LoginController {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private ToggleGroup userType;
    @FXML
    private Button btnSignin;

    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == btnSignin) {
            if (txtUsername.getText().trim().isEmpty()) {
                txtUsername.setPromptText("Username must not be empty!");
            }
            if (txtPassword.getText().trim().isEmpty()) {
                txtPassword.setPromptText("Password must not be empty!");
            }
            if (!txtUsername.getText().trim().isEmpty() || !txtPassword.getText().trim().isEmpty()) {
                RadioButton selectedRadioButton = (RadioButton) userType.getSelectedToggle();
                String selectedUserType = selectedRadioButton.getText();
                if (selectedUserType.equals("Client")) {
                    int userId = App.clientDao.login(
                            txtUsername.getText().trim(),
                            txtPassword.getText().trim());
                    if (userId != 0) {
                        App.activeUserId = userId;
                        SceneController.switchScenes(event, "client");
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Login error");
                        errorAlert.setContentText("Bad credentials. Please login again.");
                        errorAlert.showAndWait();
                    }
                } else {
                    int userId = App.employeeDao.login(
                            txtUsername.getText().trim(),
                            txtPassword.getText().trim());
                    if (userId != 0) {
                        App.activeUserId = userId;
                        SceneController.switchScenes(event, "employee");
                    } else {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setHeaderText("Login error");
                        errorAlert.setContentText("Bad credentials. Please login again.");
                        errorAlert.showAndWait();
                    }
                }
            }
        }
    }
}
