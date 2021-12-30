package controller;

import db.DbConnection;
import interfaces.LabManage;
import model.LabDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LabController implements LabManage {
    public List<String> getPatientId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM LabDetail").executeQuery();
        List<String> codes = new ArrayList<>();
        while (rst.next()) {
            codes.add(
                    rst.getString(1)
            );
        }
        return codes;
    }


    @Override
    public boolean saveDetail(LabDetail l) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO LabDetail VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, l.getPatientId());
        stm.setObject(2, l.getBloodTypes());
        stm.setObject(3, l.getTestings());
        stm.setObject(4, l.getDoctorName());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean updateDetail(LabDetail l) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE LabDetail SET bloodTypes=?, testings=?, doctorName=? WHERE patientId=?");
        stm.setObject(1, l.getBloodTypes());
        stm.setObject(2, l.getTestings());
        stm.setObject(3, l.getDoctorName());
        stm.setObject(4, l.getPatientId());


        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean deleteDetail(String patientId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM LabDetail WHERE patientId='" + patientId + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public LabDetail getDetail(String patientId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM LabDetail WHERE patientId=?");
        stm.setObject(1, patientId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new LabDetail(
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
    public ArrayList<LabDetail> getAllDetails() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM LabDetail");
        ResultSet rst = stm.executeQuery();
        ArrayList<LabDetail> details = new ArrayList<>();
        while (rst.next()) {
            details.add(new LabDetail(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)


            ));
        }
        return details;
    }
}
