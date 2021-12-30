package model;

public class LabDetail {
    private String patientId;
    private String bloodTypes;
    private String testings;
    private String doctorName;

    public LabDetail() {
    }

    public LabDetail(String patientId, String bloodTypes, String testings,  String doctorName) {
        this.setPatientId(patientId);
        this.setBloodTypes(bloodTypes);
        this.setTestings(testings);
        this.setDoctorName(doctorName);
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getBloodTypes() {
        return bloodTypes;
    }

    public void setBloodTypes(String bloodTypes) {
        this.bloodTypes = bloodTypes;
    }

    public String getTestings() {
        return testings;
    }

    public void setTestings(String testings) {
        this.testings = testings;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    @Override
    public String toString() {
        return "LabDetail{" +
                "patientId='" + patientId + '\'' +
                ", bloodTypes='" + bloodTypes + '\'' +
                ", testings='" + testings + '\'' +
                ", doctorName='" + doctorName + '\'' +
                '}';
    }
}
