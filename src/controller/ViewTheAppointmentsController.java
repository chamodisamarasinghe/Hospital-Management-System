package controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Patient;
import tm.PatientTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class ViewTheAppointmentsController extends PatientController{
    public AnchorPane viewAppointmentsContext;
    public JFXButton btn1;
    public JFXButton btn2;
    public ComboBox<String>cmbPatientNo;
    public TextField txtRoomNo;
    public TextField txtDocName;
    public TextField txtPatientName;
    public TextField txtAge;
    public TextField txtAddress;
    public TextField txtBirthday;
    public TextField txtGender;
    public TextField txtCategory;
    public TextField txtWardNo;
    public TableView tblAllAppointments;
    public TableColumn colPatientNo;
    public TableColumn colRoomNo;
    public TableColumn colDocName;
    public TableColumn colPatientName;
    public TableColumn colAge;
    public TableColumn colAddress;
    public TableColumn colBirthday;
    public TableColumn colGender;
    public TableColumn colCategory;
    public TableColumn colWardNo;
    public Label lblDate;
    public Label lblTime;
    private int cartSelectedRowForRemove=-1;

    public void initialize(){
        colPatientNo.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colRoomNo.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colDocName.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colPatientName.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colAge.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colAddress.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colBirthday.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colCategory.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colGender.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colWardNo.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");


        colPatientNo.setCellValueFactory(new PropertyValueFactory<>("patientNo"));
        colRoomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        colDocName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colBirthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        colWardNo.setCellValueFactory(new PropertyValueFactory<>("wardNo"));


        try {
            loadPatientNum();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        cmbPatientNo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setPatientData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });

        tblAllAppointments.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });


        loadDateAndTime();
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));


        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }



    private void setPatientData(String patientNo) throws SQLException, ClassNotFoundException {
        Patient p= new PatientController().getPatient(patientNo);
        if (p==null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        }else{
            txtRoomNo.setText(p.getRoomNo());
            txtDocName.setText(p.getDoctorName());
            txtPatientName.setText(p.getPatientName());
            txtAge.setText(String.valueOf(p.getAge()));
            txtAddress.setText(p.getAddress());
            txtBirthday.setText(p.getBirthday());
            txtCategory.setText(p.getCategory());
            txtGender.setText(p.getGender());
            txtWardNo.setText(p.getWardNo());
        }
    }


    private void loadPatientNum() throws SQLException, ClassNotFoundException {
        List<String> patientNum = new PatientController().getPatientNo();
        cmbPatientNo.getItems().addAll(patientNum);
    }


    public void backToRecepOnAction(ActionEvent actionEvent) throws IOException {
        btn2.setVisible(false);
        btn1.setVisible(true);
        URL resource = getClass().getResource("../views/ReceptionistForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) viewAppointmentsContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }

    public void backToDoctorOnAction(ActionEvent actionEvent) throws IOException {

        URL resource = getClass().getResource("../views/DoctorForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) viewAppointmentsContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }


    ObservableList<PatientTM> obList= FXCollections.observableArrayList();
    public void addToTableOnAction(ActionEvent actionEvent) {
        String roomNo = txtRoomNo.getText();
        String doctorName = txtDocName.getText();
        String patientName = txtPatientName.getText();
        int age = Integer.parseInt(txtAge.getText());
        String address = txtAddress.getText();
        String birthday = txtBirthday.getText();
        String category = txtCategory.getText();
        String gender = txtGender.getText();
        String wardNo = txtWardNo.getText();



        PatientTM tm = new PatientTM(
                cmbPatientNo.getValue(),
                roomNo,
                doctorName,
                patientName,
                age,
                address,
                birthday,
                category,
                gender,
                wardNo
        );
        int rowNumber=isExists(tm);

        if (rowNumber==-1){
            obList.add(tm);
        }else{

            PatientTM temp = obList.get(rowNumber);
            PatientTM newTm = new PatientTM(
                    temp.getPatientNo(),
                    temp.getRoomNo(),
                    temp.getDoctorName(),
                    temp.getPatientName(),
                    temp.getAge(),
                    temp.getAddress(),
                    temp.getBirthday(),
                    temp.getCategory(),
                    temp.getGender(),
                    temp.getWardNo()
            );
            obList.remove(rowNumber);
            obList.add(newTm);
        }
        tblAllAppointments.setItems(obList);
    }

    private int isExists(PatientTM tm) {
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getPatientNo().equals(obList.get(i).getPatientNo())){
                return i;
            }
        }
        return -1;
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{
            obList.remove(cartSelectedRowForRemove);
            tblAllAppointments.refresh();
        }
    }
}
