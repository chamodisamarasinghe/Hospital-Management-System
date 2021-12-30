package controller;

import db.DbConnection;
import interfaces.DoctorManage;
import model.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorController implements DoctorManage {

    public List<String> getDoctorId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Doctor").executeQuery();
        List<String> codes = new ArrayList<>();
        while (rst.next()) {
            codes.add(
                    rst.getString(1)
            );
        }
        return codes;
    }


    @Override
    public boolean saveDoctor(Doctor d) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Doctor VALUES(?,?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, d.getDoctorId());
        stm.setObject(2, d.getDoctorName());
        stm.setObject(3, d.getDoctorContactNo());
        stm.setObject(4, d.getWorkingDate());
        stm.setObject(5, d.getWorkingTime());
        stm.setObject(6, d.getDoctorEmail());
        stm.setObject(7, d.getRoomNo());
        stm.setObject(8, d.getCategory());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean updateDoctor(Doctor d) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Doctor SET doctorName=?, doctorContactNo=?, workingDate=?,  doctorEmail=?, roomNo=?, category=? WHERE doctorId=?");
        stm.setObject(1, d.getDoctorName());
        stm.setObject(2, d.getDoctorContactNo());
        stm.setObject(3, d.getWorkingDate());
        //stm.setObject(4, d.getWorkingTime());
        stm.setObject(4, d.getDoctorEmail());
        stm.setObject(5, d.getRoomNo());
        stm.setObject(6, d.getCategory());
        stm.setObject(7, d.getDoctorId());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean deleteDoctor(String doctorId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Doctor WHERE doctorId='" + doctorId + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Doctor getDoctor(String doctorId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Doctor WHERE doctorId=?");
        stm.setObject(1, doctorId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Doctor(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)

            );

        } else {
            return null;
        }

    }


    @Override
    public ArrayList<Doctor> getAllDoctors() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Doctor");
        ResultSet rst = stm.executeQuery();
        ArrayList<Doctor> patients = new ArrayList<>();
        while (rst.next()) {
            patients.add(new Doctor(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8)

            ));
        }
        return patients;
    }

}
