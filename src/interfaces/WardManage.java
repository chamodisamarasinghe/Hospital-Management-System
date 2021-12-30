package interfaces;

import model.Nurse;
import model.Ward;

import java.sql.SQLException;
import java.util.ArrayList;

public interface WardManage {
    boolean saveWard(Ward w) throws SQLException, ClassNotFoundException;
    boolean updateWard(Ward w) throws SQLException, ClassNotFoundException;
    public boolean deleteWard(String PatientId) throws SQLException, ClassNotFoundException;
    public Ward getWard(String PatientId) throws SQLException, ClassNotFoundException;
    public ArrayList<Ward> getAllWards() throws SQLException, ClassNotFoundException;
}
