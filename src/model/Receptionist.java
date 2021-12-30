package model;

public class Receptionist {
    private String recepId;
    private String patientNo;
    private String recepContactNo;

    public Receptionist() {
    }

    public Receptionist(String recepId, String patientNo, String recepContactNo) {
        this.setRecepId(recepId);
        this.setPatientNo(patientNo);
        this.setRecepContactNo(recepContactNo);
    }

    public String getRecepId() {
        return recepId;
    }

    public void setRecepId(String recepId) {
        this.recepId = recepId;
    }

    public String getPatientNo() {
        return patientNo;
    }

    public void setPatientNo(String patientNo) {
        this.patientNo = patientNo;
    }

    public String getRecepContactNo() {
        return recepContactNo;
    }

    public void setRecepContactNo(String recepContactNo) {
        this.recepContactNo = recepContactNo;

    }

    @Override
    public String toString() {
        return "Receptionist{" +
                "recepId='" + recepId + '\'' +
                ", patientNo='" + patientNo + '\'' +
                ", recepContactNo='" + recepContactNo + '\'' +
                '}';
    }


}
