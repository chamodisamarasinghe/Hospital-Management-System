package interfaces;

import model.Doctor;
import model.Patient;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DoctorManage {
    boolean saveDoctor(Doctor d) throws SQLException, ClassNotFoundException;
    boolean updateDoctor(Doctor d) throws SQLException, ClassNotFoundException;
    public boolean deleteDoctor(String doctorId) throws SQLException, ClassNotFoundException;
    public Doctor getDoctor(String doctorId) throws SQLException, ClassNotFoundException;
    public ArrayList<Doctor> getAllDoctors() throws SQLException, ClassNotFoundException;
}
