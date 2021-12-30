package controller;

import db.DbConnection;
import interfaces.PaymentManage;
import model.Doctor;
import model.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentController implements PaymentManage {
    public List<String> getBillNo() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Payment").executeQuery();
        List<String> codes = new ArrayList<>();
        while (rst.next()) {
            codes.add(
                    rst.getString(1)
            );
        }
        return codes;
    }


    @Override
    public boolean savePayment(Payment y) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Payment VALUES(?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, y.getBillNo());
        stm.setObject(2, y.getDoctorName());
        stm.setObject(3, y.getCategory());
        stm.setObject(4, y.getRoomCharges());
        stm.setObject(5, y.getDoctorCharge());
        stm.setObject(6, y.getTestings());


        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean updatePayment(Payment y) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Payment SET doctorName=?, category=?, roomCharges=?, doctorCharge=?, testings=? WHERE billNo=?");
        stm.setObject(1, y.getDoctorName());
        stm.setObject(2, y.getCategory());
        stm.setObject(3, y.getRoomCharges());
        stm.setObject(4, y.getDoctorCharge());
        stm.setObject(5, y.getTestings());
        stm.setObject(6, y.getBillNo());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean deletePayment(String billNo) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Payment WHERE billNo='" + billNo + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Payment getPayment(String billNo) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Payment WHERE billNo=?");
        stm.setObject(1, billNo);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Payment(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getInt(5),
                    rst.getInt(6)


            );

        } else {
            return null;
        }

    }


    @Override
    public ArrayList<Payment> getAllPayments() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Payment");
        ResultSet rst = stm.executeQuery();
        ArrayList<Payment> payments = new ArrayList<>();
        while (rst.next()) {
            payments.add(new Payment(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getInt(5),
                    rst.getInt(6)

            ));
        }
        return payments;
    }
}
