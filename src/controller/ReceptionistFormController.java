package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ReceptionistFormController {
    public AnchorPane receptionistFormContext;

    public void logOutOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/DashBoardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) receptionistFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void openAppointmentFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ManageAllTheAppointmentsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) receptionistFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void openPaymentsFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ManageThePaymentsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) receptionistFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void openAllTheAppointmentsFormOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ViewTheAppointments.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) receptionistFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void openNurseDetailsOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ManageTheNurseForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) receptionistFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
