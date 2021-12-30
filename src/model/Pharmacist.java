package model;

public class Pharmacist {
    private String pharmId;
    private String pharmName;
    private String drugName;
    private String refId;

    public Pharmacist() {
    }

    public Pharmacist(String pharmId, String pharmName, String drugName, String refId) {
        this.setPharmId(pharmId);
        this.setPharmName(pharmName);
        this.setDrugName(drugName);
        this.setRefId(refId);
    }

    public String getPharmId() {
        return pharmId;
    }

    public void setPharmId(String pharmId) {
        this.pharmId = pharmId;
    }

    public String getPharmName() {
        return pharmName;
    }

    public void setPharmName(String pharmName) {
        this.pharmName = pharmName;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }


    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    @Override
    public String toString() {
        return "Pharmacist{" +
                "pharmId='" + pharmId + '\'' +
                ", pharmName='" + pharmName + '\'' +
                ", drugName='" + drugName + '\'' +
                ", refId='" + refId + '\'' +
                '}';
    }


}
