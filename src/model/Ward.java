package model;

import java.util.Objects;

public class Ward {
    private String patientId;
    private String bedNo;
    private String admitDate;
    private String availabilityOfRooms;

    public Ward() {
    }

    public Ward(String patientId, String bedNo, String admitDate, String availabilityOfRooms) {
        this.setPatientId(patientId);
        this.setBedNo(bedNo);
        this.setAdmitDate(admitDate);
        this.setAvailabilityOfRooms(availabilityOfRooms);
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }


    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getAdmitDate() {
        return admitDate;
    }

    public void setAdmitDate(String admitDate) {
        this.admitDate = admitDate;
    }


    public String getAvailabilityOfRooms() {
        return availabilityOfRooms;
    }

    public void setAvailabilityOfRooms(String availabilityOfRooms) {
        this.availabilityOfRooms = availabilityOfRooms;
    }

    @Override
    public String toString() {
        return "Ward{" +
                "patientId='" + patientId + '\'' +
                ", bedNo='" + bedNo + '\'' +
                ", admitDate='" + admitDate + '\'' +
                ", availabilityOfRooms='" + availabilityOfRooms + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ward ward = (Ward) o;
        return patientId.equals(ward.patientId) &&
                bedNo.equals(ward.bedNo) &&
                admitDate.equals(ward.admitDate) &&
                availabilityOfRooms.equals(ward.availabilityOfRooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, bedNo, admitDate, availabilityOfRooms);
    }
}
