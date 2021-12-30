package util;

import db.DbConnection;
import model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataBaseAccessCode {
    public ArrayList<NewOrder> getAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList<NewOrder> list = new ArrayList();
        ResultSet resultSet = DbConnection.getInstance().getConnection().
                prepareStatement
                        ("SELECT p.pharmId, p.pharmName, o.orderId, o.orderDate,o.cost FROM Pharmacist p JOIN `Order` o ON o.pharmId=p.pharmId")
                .executeQuery();
        while (resultSet.next()) {
            list.add(
                    new NewOrder(resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    )
            );
        }
        return list;
    }


    public ArrayList<OrderDetail> getAllOrderDetails(String orderId) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetail> details = new ArrayList<>();
        ResultSet resultSet = DbConnection.getInstance().getConnection()
                .prepareStatement("SELECT productCode, qty, price FROM MedicalDetail WHERE orderId='"
                        + orderId + "'").executeQuery();
        while (resultSet.next()) {
            details.add(
                    new OrderDetail(
                            resultSet.getString(1),
                            resultSet.getInt(2),
                            resultSet.getDouble(3)
                    )
            );
        }
        return details;
    }


    public ArrayList<Appointments1> getAllAppointments() throws SQLException, ClassNotFoundException {
        ArrayList<Appointments1> list = new ArrayList();
        ResultSet resultSet = DbConnection.getInstance().getConnection().
                prepareStatement
                        ("SELECT p.patientNo, p.patientName, a.appointmentNo, a.appointmentDate,a.cost FROM Patient p JOIN `Appointment` a ON a.patientNo=p.patientNo")
                .executeQuery();
        while (resultSet.next()) {
            list.add(
                    new Appointments1(resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    )
            );
        }
        return list;

    }

    public ArrayList<AppointmentDetails1> getAllAppointmentDetail(String appointmentNo) throws SQLException, ClassNotFoundException {
        ArrayList<AppointmentDetails1> details = new ArrayList<>();
        ResultSet resultSet = DbConnection.getInstance().getConnection()
                .prepareStatement("SELECT billNo, roomCharges, doctorCharge, testings FROM `Appointment Detail` WHERE appointmentNo='"
                        + appointmentNo + "'").executeQuery();
        while (resultSet.next()) {

            System.out.println(resultSet.getString(1));
            System.out.println(resultSet.getDouble(2));
            System.out.println(resultSet.getDouble(3));
            System.out.println(resultSet.getDouble(4));

            details.add(
                    new AppointmentDetails1(
                            resultSet.getString(1),
                            resultSet.getDouble(2),
                            resultSet.getDouble(3),
                            resultSet.getDouble(4)
                    )
            );
        }
        return details;
    }

}
