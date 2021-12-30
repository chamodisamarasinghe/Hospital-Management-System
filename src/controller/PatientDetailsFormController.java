package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AppointmentDetails;
import model.AppointmentDetails1;
import model.OrderDetail;
import tm.AppointmentDetailTM;
import tm.OrderDetailTM;
import util.DataBaseAccessCode;

import java.sql.SQLException;

public class PatientDetailsFormController {
    public Label lblAppointmentNo;
    public TableView<AppointmentDetailTM> tblPatientDetails;
    public TableColumn colBillNo;
    public TableColumn colRoomCharges;
    public TableColumn colDoctorCharges;
    public TableColumn colTestingCharges;
    public Label lblTotal;


    public void initialize(){
        colBillNo.setCellValueFactory(new PropertyValueFactory<>("billNo"));
        colRoomCharges.setCellValueFactory(new PropertyValueFactory<>("roomCharges"));
        colDoctorCharges.setCellValueFactory(new PropertyValueFactory<>("doctorCharge "));
        colTestingCharges.setCellValueFactory(new PropertyValueFactory<>("testings"));



    }





    public void loadAllAppointments(String no){
        lblAppointmentNo.setText(no);
        double total=0;
        try {
            ObservableList<AppointmentDetailTM> tmList = FXCollections.observableArrayList();
            for (AppointmentDetails1 tempA : new DataBaseAccessCode().getAllAppointmentDetail(no)
            ) {
                total=tempA.getRoomCharges()+tempA.getDoctorCharge()+tempA.getTestings();
                tmList.add(new AppointmentDetailTM(
                        tempA.getBillNo(), tempA.getRoomCharges(), tempA.getDoctorCharge(), tempA.getTestings()));
            }
            tblPatientDetails.setItems(tmList);
            lblTotal.setText(total+"/=");

            new DataBaseAccessCode().getAllAppointmentDetail(no);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    }

