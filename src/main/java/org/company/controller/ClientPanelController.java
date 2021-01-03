package org.company.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import org.company.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.company.model.Car;

import java.io.IOException;
import java.util.List;

public class ClientPanelController {
    @FXML
    private Button btnShoppingCart;

    @FXML
    private Button btnShowCars;

    @FXML
    private Button btnLogOut;

    @FXML
    private TableView<Car> tableCars;

    @FXML
    private TableColumn<Car, Integer> car_ID_col;

    @FXML
    private TableColumn<Car, String> brand_col;

    @FXML
    private TableColumn<Car, String> model_col;

    @FXML
    private TableColumn<Car, String> engine_type_col;

    @FXML
    private TableColumn<Car, Float> engine_cap_col;

    @FXML
    private TableColumn<Car, Float> mileage_col;

    @FXML
    private TableColumn<Car, Integer> year_col;

    @FXML
    private TableColumn<Car, Integer> price_col;

    @FXML
    private TableColumn<Car, Boolean> availability_col;

    ObservableList<Car> carsObservableList;

    @FXML
    void btnShowCarsActionHandler (ActionEvent event) throws IOException{

        List<Car> carsList = App.carDao.getAll();
        carsObservableList = FXCollections.observableArrayList(carsList);

        car_ID_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        brand_col.setCellValueFactory(new PropertyValueFactory<>("brand"));
        model_col.setCellValueFactory(new PropertyValueFactory<>("model"));
        engine_type_col.setCellValueFactory(new PropertyValueFactory<>("engineType"));
        engine_cap_col.setCellValueFactory(new PropertyValueFactory<>("engineCapacity"));
        mileage_col.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        year_col.setCellValueFactory(new PropertyValueFactory<>("productionYear"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("price"));
        availability_col.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));

        tableCars.setItems(carsObservableList);

    }
    @FXML
    void btnShoppingCartActionHandler(ActionEvent event)throws IOException {
        if(event.getSource()==btnShoppingCart){
            SceneController.switchScenes(event, "");
        }
    }

    @FXML
    void btnLogOutActionHandler(ActionEvent event) throws IOException {
        App.activeUserId = 0;
        SceneController.switchScenes(event,"login","view/css/login.css");
    }


}
