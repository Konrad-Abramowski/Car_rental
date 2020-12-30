package org.company;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.company.controller.SceneController;
import org.company.dao.AccountDao;
import org.company.dao.AddressDao;
import org.company.model.Address;

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