package org.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.company.controller.SceneController;
import org.company.dao.*;
import org.company.model.Address;
import org.company.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;

/**
 * JavaFX App
 */
public class App extends Application {

    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Entities");
    public static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static AccountDao accountDao = new AccountDao(entityManager);
    public static AddressDao addressDao = new AddressDao(entityManager);
    public static CarDao carDao = new CarDao(entityManager);
    public static ClientDao clientDao = new ClientDao(entityManager);
    public static EmployeeDao employeeDao = new EmployeeDao(entityManager);
    public static LoanDao loanDao = new LoanDao(entityManager);

    public static int activeUserId;

    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = SceneController.getSceneFromFxml("login");
        scene.getStylesheets().add("view/css/login.css");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}