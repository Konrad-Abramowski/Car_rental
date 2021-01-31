package org.company.controller;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.company.App;
import org.company.model.Car;

import java.io.IOException;
import java.util.List;

public class EmployeePanelController {

    @FXML
    private TableView<Car> tableCars;

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

    @FXML
    private TableColumn<Car, Void> btnAdd_col;

    @FXML
    void btnShowCarsActionHandler() {
        showCarsInClientPanel();
    }
    @FXML
    void btnAddCarActionHandler(ActionEvent event)throws IOException {
        SceneController.switchScenes(event, "add_car");
    }

    @FXML
    void btnLogOutActionHandler(ActionEvent event) throws IOException {
        App.activeUserId = 0;
        SceneController.switchScenes(event,"login","view/css/login.css");
    }

    public void initialize(){
        showCarsInClientPanel();
    }

    private void showCarsInClientPanel(){

        List<Car> carsList = App.carDao.getAll();
        ObservableList<Car> carsObservableList = FXCollections.observableArrayList(carsList);

        // car_ID_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        brand_col.setCellValueFactory(new PropertyValueFactory<>("brand"));
        model_col.setCellValueFactory(new PropertyValueFactory<>("model"));
        engine_type_col.setCellValueFactory(new PropertyValueFactory<>("engineType"));
        engine_cap_col.setCellValueFactory(new PropertyValueFactory<>("engineCapacity"));
        mileage_col.setCellValueFactory(new PropertyValueFactory<>("mileage"));
        year_col.setCellValueFactory(new PropertyValueFactory<>("productionYear"));
        price_col.setCellValueFactory(new PropertyValueFactory<>("price"));
        availability_col.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isAvailable()));
        availability_col.setCellFactory(col -> new TableCell<>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty) ;
                setText(empty ? null : item ? "Available" : "Unavailable" );
            }
        });
        tableCars.setItems(carsObservableList);
        addButtonsToTable();
    }

    private void addButtonsToTable() {

        Callback<TableColumn<Car, Void>, TableCell<Car, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Car, Void> call(final TableColumn<Car, Void> param) {
                return new TableCell<>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Car car = getTableView().getItems().get(getIndex());
                            App.carDao.delete(car);
                            showCarsInClientPanel();
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
            }
        };

        btnAdd_col.setCellFactory(cellFactory);

    }
}
