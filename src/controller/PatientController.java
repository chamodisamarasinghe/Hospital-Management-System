package controller;

import db.DbConnection;
import interfaces.PatientManage;
import model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientController implements PatientManage {

    public List<String> getPatientNo() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Patient").executeQuery();
        List<String> codes = new ArrayList<>();
        while (rst.next()) {
            codes.add(
                    rst.getString(1)
            );
        }
        return codes;
    }


    @Override
    public boolean savePatient(Patient p) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Patient VALUES(?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, p.getPatientNo());
        stm.setObject(2, p.getRoomNo());
        stm.setObject(3, p.getDoctorName());
        stm.setObject(4, p.getPatientName());
        stm.setObject(5, p.getAge());
        stm.setObject(6, p.getAddress());
        stm.setObject(7, p.getBirthday());
        stm.setObject(8, p.getCategory());
        stm.setObject(9, p.getGender());
        stm.setObject(10, p.getWardNo());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean updatePatient(Patient p) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Patient SET roomNo=?,doctorName=?, patientName=?, age=?,address=?,birthday=?,category=?,gender=?,wardNo=? WHERE patientNo=?");
        stm.setObject(1, p.getRoomNo());
        stm.setObject(2, p.getDoctorName());
        stm.setObject(3, p.getPatientName());
        stm.setObject(4, p.getAge());
        stm.setObject(5, p.getAddress());
        stm.setObject(6, p.getBirthday());
        stm.setObject(7, p.getCategory());
        stm.setObject(8, p.getGender());
        stm.setObject(9, p.getWardNo());
        stm.setObject(10, p.getPatientNo());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean deletePatient(String patientNo) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Patient WHERE patientNo='" + patientNo + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Patient getPatient(String patientNo) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Patient WHERE patientNo=?");
        stm.setObject(1, patientNo);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Patient(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getString(8),
                    rst.getString(9),
                    rst.getString(10)

            );

        } else {
            return null;
        }

    }


        @Override
        public ArrayList<Patient> getAllPatients () throws SQLException, ClassNotFoundException {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Patient");
            ResultSet rst = stm.executeQuery();
            ArrayList<Patient> patients = new ArrayList<>();
            while (rst.next()) {
                patients.add(new Patient(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getInt(5),
                        rst.getString(6),
                        rst.getString(7),
                        rst.getString(8),
                        rst.getString(9),
                        rst.getString(10)

                ));
            }
            return patients;
        }

        }


