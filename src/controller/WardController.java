package controller;

import db.DbConnection;
import interfaces.WardManage;
import model.Ward;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WardController implements WardManage {
    public List<String> getWard() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Ward").executeQuery();
        List<String> codes = new ArrayList<>();
        while (rst.next()) {
            codes.add(
                    rst.getString(1)
            );
        }
        return codes;
    }
    @Override
    public boolean saveWard(Ward w) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Ward VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, w.getPatientId());
        stm.setObject(2, w.getBedNo());
        stm.setObject(3, w.getAdmitDate());
        stm.setObject(4, w.getAvailabilityOfRooms());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean updateWard(Ward w) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Ward SET bedNo=?, admitDate=?, availabilityOfRooms=? WHERE patientId=?");
        stm.setObject(1, w.getBedNo());
        stm.setObject(2, w.getAdmitDate());
        stm.setObject(3, w.getAvailabilityOfRooms());
        stm.setObject(4, w.getPatientId());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean deleteWard(String patientId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Ward WHERE patientId='" + patientId + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Ward getWard(String patientId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Ward WHERE patientId=?");
        stm.setObject(1, patientId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Ward(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)

            );

        } else {
            return null;
        }

    }


    @Override
    public ArrayList<Ward> getAllWards() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Ward");
        ResultSet rst = stm.executeQuery();
        ArrayList<Ward> wards = new ArrayList<>();
        while (rst.next()) {
            wards.add(new Ward(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)


            ));
        }
        return wards;
    }
}
