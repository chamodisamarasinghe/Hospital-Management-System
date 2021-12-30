package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class AdminFormController {
    public AnchorPane adminFormContext;


    public void goBackOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) adminFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void openManageDoctorFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ManageTheDoctorsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) adminFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void openManageWardFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/WardDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) adminFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void openIncomeReportsFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/IncomeReportsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) adminFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void openManagePharmacyFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ManageThePharmacy.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) adminFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void viewDoctorDetailsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ViewDoctorDetailsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) adminFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
