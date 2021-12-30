package controller;

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
import model.Doctor;
import tm.DoctorTM;
import tm.PatientTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class ViewDoctorDetailsFormController extends DoctorController{
    public AnchorPane viewDoctorFormContext;
    public ComboBox<String> cmbDocId;
    public TextField txtName;
    public TextField txtContactNo;
    public TextField txtDate;
    public TextField txtTime;
    public TextField txtEmail;
    public TextField txtRoomNo;
    public TextField txtCategory;
    public TableView tblDoctorDetail;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colContact;
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colEmail;
    public TableColumn colRoomNo;
    public TableColumn colCategory;
    public Label lblDate;
    public Label lblTime;
    private int cartSelectedRowForRemove=-1;


    public void initialize(){
        colId.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colName.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colContact.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colDate.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colTime.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colEmail.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colRoomNo.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");
        colCategory.setStyle("-fx-border-color: black;-fx-table-cell-border-color:black;");



        colId.setCellValueFactory(new PropertyValueFactory<>("doctorId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("doctorContactNo"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("workingDate"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("workingTime"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("doctorEmail"));
        colRoomNo.setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        try {
            loadDoctorId();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        cmbDocId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setDoctorData(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        });

        tblDoctorDetail.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
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

    private void setDoctorData(String doctorId) throws SQLException, ClassNotFoundException {
        Doctor d= new DoctorController().getDoctor(doctorId);
        if (d==null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        }else{
            txtName.setText(d.getDoctorName());
            txtContactNo.setText(d.getDoctorContactNo());
            txtDate.setText(d.getWorkingDate());
            txtTime.setText(d.getWorkingTime());
            txtEmail.setText(d.getDoctorEmail());
            txtRoomNo.setText(d.getRoomNo());
            txtCategory.setText(d.getCategory());

        }
    }




    private void loadDoctorId() throws SQLException, ClassNotFoundException {
        List<String> doctorId = new DoctorController().getDoctorId();
        cmbDocId.getItems().addAll(doctorId);
    }

    ObservableList<DoctorTM> obList= FXCollections.observableArrayList();
    public void addToTableOnAction(ActionEvent actionEvent) {
        String doctorName = txtName.getText();
        String doctorContactNo = txtContactNo.getText();
        String workingDate = txtDate.getText();
        String workingTime = txtTime.getText();
        String Email = txtEmail.getText();
        String roomNo = txtRoomNo.getText();
        String category = txtCategory.getText();

        DoctorTM tm = new DoctorTM(
                cmbDocId.getValue(),
                doctorName,
                doctorContactNo,
                workingDate,
                workingTime,
                Email,
                roomNo,
                category

        );
        int rowNumber=isExists(tm);

        if (rowNumber==-1){
            obList.add(tm);
        }else{

            DoctorTM temp = obList.get(rowNumber);
            DoctorTM newTm = new DoctorTM(
                    temp.getDoctorId(),
                    temp.getDoctorName(),
                    temp.getDoctorContactNo(),
                    temp.getWorkingDate(),
                    temp.getWorkingTime(),
                    temp.getDoctorEmail(),
                    temp.getRoomNo(),
                    temp.getCategory()

            );
            obList.remove(rowNumber);
            obList.add(newTm);
        }
        tblDoctorDetail.setItems(obList);
    }

    private int isExists(DoctorTM tm) {
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getDoctorId().equals(obList.get(i).getDoctorId())){
                return i;
            }
        }
        return -1;
    }

    public void removeOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{
            obList.remove(cartSelectedRowForRemove);
            tblDoctorDetail.refresh();
        }
    }

    public void backToDoctorAdminOnAction(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../views/AdminForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Stage window = (Stage) viewDoctorFormContext.getScene().getWindow();
        window.setScene(new Scene(load));
    }
}
