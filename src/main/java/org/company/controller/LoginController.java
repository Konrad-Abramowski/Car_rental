package org.company.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.company.App;


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
                    //ToDo client login

                } else {
                    //ToDo employee login

                }
            }
        }
    }
}
