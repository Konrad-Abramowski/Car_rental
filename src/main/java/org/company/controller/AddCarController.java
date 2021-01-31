package org.company.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import org.company.App;
import org.company.model.Car;


import java.io.IOException;

public class AddCarController {
    @FXML
    public JFXTextField brandTF;
    @FXML
    public JFXTextField modelTF;
    @FXML
    public JFXTextField engineTypeTF;
    @FXML
    public JFXTextField mileageTF;
    @FXML
    public JFXTextField countryOfOriginTF;
    @FXML
    public JFXTextField productionYearTF;
    @FXML
    public JFXTextField vinTF;
    @FXML
    public JFXTextField engineCapacityTF;
    @FXML
    public JFXTextField priceTF;
    @FXML
    public JFXTextField colorTF;


    @FXML
    private Button addClientBtn;
    @FXML
    private Button returnBtn;

    public void handleButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == addClientBtn) {
            String brand = brandTF.getText().trim();
            String model = modelTF.getText().trim();
            String engineType = engineTypeTF.getText().trim();
            String mileage = mileageTF.getText().trim();
            String countryOfOrigin = countryOfOriginTF.getText().trim();
            String productionYear = productionYearTF.getText().trim();
            String vin = vinTF.getText().trim();
            String engineCapacity = engineCapacityTF.getText().trim();
            String price = priceTF.getText().trim();
            String color = colorTF.getText().trim();
            if (brand.isEmpty()) {
                brandTF.setPromptText("Car's brand must not be empty!");
            }
            if (model.isEmpty()) {
                modelTF.setPromptText("Car's model must not be empty!");
            }
            if (engineType.isEmpty()) {
                engineTypeTF.setPromptText("Car's engine type must not be empty!");
            }
            if (mileage.isEmpty()) {
                mileageTF.setPromptText("Car's mileage must not be empty!");
            }
            if (countryOfOrigin.isEmpty()) {
                countryOfOriginTF.setPromptText("Car's country of origin must not be empty!");
            }
            if (productionYear.isEmpty()) {
                productionYearTF.setPromptText("Car's production year name must not be empty!");
            }
            if (vin.isEmpty()) {
                vinTF.setPromptText("Car's vin must not be empty!");
            }
            if (engineCapacity.isEmpty()) {
                engineCapacityTF.setPromptText("Car's engine capacity must not be empty!");
            }
            if (price.isEmpty()) {
                priceTF.setPromptText("Car's price must not be empty!");
            }
            if (color.isEmpty()) {
                colorTF.setPromptText("Car's color must not be empty!");
            }
            if (!brand.isEmpty() && !model.isEmpty()
                    && !engineType.isEmpty() && !mileage.isEmpty()
                    && !countryOfOrigin.isEmpty() && !productionYear.isEmpty()
                    && !vin.isEmpty() && !engineCapacity.isEmpty()
                    && !price.isEmpty() && !color.isEmpty()) {
                Car car = new Car(brand,model, color,engineType,Float.parseFloat(engineCapacity),countryOfOrigin,Float.parseFloat(mileage),Integer.parseInt(productionYear),vin,Integer.parseInt(price),true);
                App.carDao.create(car);
                SceneController.switchScenes(event, "employee_panel", "view/css/login.css");
            }
        } else if (event.getSource() == returnBtn){
            SceneController.switchScenes(event, "employee_panel", "view/css/login.css");
        }
    }
}
