package interfaces;

import model.Doctor;
import model.Nurse;
import model.Patient;

import java.sql.SQLException;
import java.util.ArrayList;

public interface NurseManage {
    boolean saveNurse(Nurse n) throws SQLException, ClassNotFoundException;
    boolean updateNurse(Nurse n) throws SQLException, ClassNotFoundException;
    public boolean deleteNurse(String nurseId) throws SQLException, ClassNotFoundException;
    public Nurse getNurse(String nurseId) throws SQLException, ClassNotFoundException;
    public ArrayList<Nurse> getAllNurses() throws SQLException, ClassNotFoundException;
}
