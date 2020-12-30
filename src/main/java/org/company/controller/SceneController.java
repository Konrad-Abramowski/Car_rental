package org.company.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class SceneController {
    public static Scene getSceneFromFxml(String fxml) throws IOException {
        String path = "src/main/resources/view/" + fxml +".fxml";
        FXMLLoader loader = new FXMLLoader();
        FileInputStream loginFxml =
                new FileInputStream(new File(path));
        Parent root = loader.load(loginFxml);
        return  new Scene(root);
    }

    public static void switchScenes(ActionEvent event, String fxml) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        //stage.setMaximized(true);
        Scene scene = SceneController.getSceneFromFxml(fxml);
        stage.setScene(scene);
        stage.show();
    }
}
