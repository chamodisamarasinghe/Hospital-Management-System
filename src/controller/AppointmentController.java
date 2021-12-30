package controller;

import db.DbConnection;
import model.Appointment;
import model.AppointmentDetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentController {
    public String getAppointmentNo() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT appointmentNo FROM `Appointment` ORDER BY appointmentNo DESC LIMIT 1"
                ).executeQuery();
        if (rst.next()) {

            int tempNo = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempNo = tempNo + 1;
            if (tempNo <= 9) {
                return "A-00" + tempNo;
            } else if (tempNo <= 99) {
                return "A-0" + tempNo;
            } else {
                return "A-" + tempNo;
            }

        } else {
            return "A-001";
        }
        /*if (rst.next()){

            int tempId = Integer.
                    parseInt(rst.getString(1).split("-")[1]);
            tempId=tempId+1;
            if (tempId<=9){
                return "A-00"+tempId;
            }else if(tempId<=99){
                return "A-0"+tempId;
            }else{
                return "A-"+tempId;
            }

        }else{
            System.out.println("56599879494696");
            return "A-001";
        }*/

    }

    public boolean placeAppointment(Appointment appointment) {
        Connection con = null;
        try {
            con = DbConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            PreparedStatement stm = con.
                    prepareStatement("INSERT INTO `Appointment` VALUES(?,?,?,?,?)");


            stm.setObject(1, appointment.getAppointmentNo());
            stm.setObject(2, appointment.getPatientNo());
            stm.setObject(3, appointment.getAppointmentDate());
            stm.setObject(4, appointment.getTime());
            stm.setObject(5, appointment.getCost());

            if (stm.executeUpdate() > 0) {
                if (saveAppointmentDetail(appointment.getAppointmentNo(), appointment.getPatients())) {
                    con.commit();
                    return true;
                } else {
                    con.rollback();
                    return false;
                }
            } else {
                con.rollback();
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {

                con.setAutoCommit(true);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

        return false;
    }

    private boolean saveAppointmentDetail(String appointNo, ArrayList<AppointmentDetails> patients) throws SQLException, ClassNotFoundException {
        for (AppointmentDetails temp : patients
        ) {
            PreparedStatement stm = DbConnection.getInstance().
                    getConnection().
                    prepareStatement("INSERT INTO `Appointment Detail` VALUES(?,?,?,?,?)");
            stm.setObject(1, appointNo);
            stm.setObject(2,temp.getBillNo());
            stm.setObject(3, temp.getRoomCharges());
            stm.setObject(4, temp.getDoctorCharge());
            stm.setObject(5, temp.getTestings());
            if (stm.executeUpdate()>0){


            } else {
                return false;
            }
        }
        return true;

    }
        }






