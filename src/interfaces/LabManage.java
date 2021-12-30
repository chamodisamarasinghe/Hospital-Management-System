package interfaces;

import model.Doctor;
import model.LabDetail;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LabManage {
    boolean saveDetail(LabDetail l) throws SQLException, ClassNotFoundException;
    boolean updateDetail(LabDetail l) throws SQLException, ClassNotFoundException;
    public boolean deleteDetail(String patientId) throws SQLException, ClassNotFoundException;
    public LabDetail getDetail(String patientId) throws SQLException, ClassNotFoundException;
    public ArrayList<LabDetail> getAllDetails() throws SQLException, ClassNotFoundException;
}
