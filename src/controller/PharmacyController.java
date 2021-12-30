package controller;

import db.DbConnection;
import interfaces.PharmacyManage;
import model.Pharmacist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PharmacyController implements PharmacyManage {
    public List<String> getPharmId() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().
                getConnection().prepareStatement("SELECT * FROM Pharmacist").executeQuery();
        List<String> codes = new ArrayList<>();
        while (rst.next()) {
            codes.add(
                    rst.getString(1)
            );
        }
        return codes;
    }


    @Override
    public boolean savePharmacist(Pharmacist h) throws SQLException, ClassNotFoundException {
        Connection con = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Pharmacist VALUES(?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1, h.getPharmId());
        stm.setObject(2, h.getPharmName());
        stm.setObject(3, h.getDrugName());
        stm.setObject(4, h.getRefId());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean updatePharmacist(Pharmacist h) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Pharmacist SET pharmName=?, drugName=?, refId=? WHERE pharmId=?");
        stm.setObject(1, h.getPharmName());
        stm.setObject(2, h.getDrugName());
        stm.setObject(3, h.getRefId());
        stm.setObject(4, h.getPharmId());

        return stm.executeUpdate() > 0;
    }

    @Override
    public boolean deletePharmacist(String pharmId) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Pharmacist WHERE pharmId='" + pharmId + "'").executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Pharmacist getPharmacist(String pharmId) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance()
                .getConnection().prepareStatement("SELECT * FROM Pharmacist WHERE pharmId=?");
        stm.setObject(1, pharmId);

        ResultSet rst = stm.executeQuery();
        if (rst.next()) {
            return new Pharmacist(
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
    public ArrayList<Pharmacist> getAllPharmacists() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Pharmacist");
        ResultSet rst = stm.executeQuery();
        ArrayList<Pharmacist> pharmacists = new ArrayList<>();
        while (rst.next()) {
            pharmacists.add(new Pharmacist(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)



            ));
        }
        return pharmacists;
    }

}
