package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Appointments1;
import model.NewOrder;
import tm.AppointmentTM;
import tm.OrderTM;
import util.DataBaseAccessCode;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

public class AppointmentFormController {
    public AnchorPane appointmentFormContext;
    public TableView<AppointmentTM> tblAppointments;
    public TableColumn colPatientNo;
    public TableColumn colPatientName;
    public TableColumn colAppointmentNo;
    public TableColumn colAppointmentDate;
    public TableColumn colPayment;


    public void initialize(){
        colPatientNo.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colPatientName.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colAppointmentNo.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colAppointmentDate.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colPayment.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");




        colPatientNo.setCellValueFactory(new PropertyValueFactory<>("patientNo"));
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colAppointmentNo.setCellValueFactory(new PropertyValueFactory<>("appointmentNo"));
        colAppointmentDate.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        colPayment.setCellValueFactory(new PropertyValueFactory<>("cost"));


        try {
            loadAllAppointments();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tblAppointments.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    try {
                        loadDetailsUi(newValue.getAppointmentNo());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });


    }

    private void loadDetailsUi(String appointmentNo) throws IOException {
        FXMLLoader fxmlLoader =
                new FXMLLoader(getClass().getResource("../views/PatientDetailsForm.fxml"));
        Parent load = fxmlLoader.load();
        PatientDetailsFormController controller = fxmlLoader.getController();
        controller.loadAllAppointments(appointmentNo);
        Stage stage = new Stage();
        stage.setScene(new Scene(load));
        stage.show();
    }





    private void loadAllAppointments() throws SQLException, ClassNotFoundException {
        ObservableList<AppointmentTM> tmList = FXCollections.observableArrayList();
        for (Appointments1 tempAppointments1:new DataBaseAccessCode().getAllAppointments()
        ) {
            tmList.add(
                    new AppointmentTM(tempAppointments1.getPatientNo(),
                            tempAppointments1.getPatientName(),
                            tempAppointments1.getAppointmentNo(),
                            tempAppointments1.getAppointmentDate(),
                            tempAppointments1.getCost())
            );
        }

        tblAppointments.setItems(tmList);


    }


    public void backToOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/ManageThePaymentsForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) appointmentFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
