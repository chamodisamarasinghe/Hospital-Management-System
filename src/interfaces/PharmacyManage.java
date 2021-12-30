package interfaces;

import model.Pharmacist;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PharmacyManage {
    boolean savePharmacist(Pharmacist h) throws SQLException, ClassNotFoundException;
    boolean updatePharmacist(Pharmacist h) throws SQLException, ClassNotFoundException;
    public boolean deletePharmacist(String pharmId) throws SQLException, ClassNotFoundException;
    public Pharmacist getPharmacist(String pharmId) throws SQLException, ClassNotFoundException;
    public ArrayList<Pharmacist> getAllPharmacists() throws SQLException, ClassNotFoundException;
}
