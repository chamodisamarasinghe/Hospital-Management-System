package interfaces;

import model.Patient;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientManage {
    boolean savePatient(Patient p) throws SQLException, ClassNotFoundException;
    boolean updatePatient(Patient p) throws SQLException, ClassNotFoundException;
    public boolean deletePatient(String patientNo) throws SQLException, ClassNotFoundException;
    public Patient getPatient(String patientNo) throws SQLException, ClassNotFoundException;
    public ArrayList<Patient> getAllPatients() throws SQLException, ClassNotFoundException;
}
