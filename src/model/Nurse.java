package model;

import java.util.Objects;

public class Nurse {
    private String nurseId;
    private String nurseName;
    private String roomNo;
    private String wardNo;

    public Nurse() {
    }

    public Nurse(String nurseId, String nurseName,  String roomNo, String wardNo) {
        this.setNurseId(nurseId);
        this.setNurseName(nurseName);
        this.setRoomNo(roomNo);
        this.setWardNo(wardNo);
    }

    public String getNurseId() {
        return nurseId;
    }

    public void setNurseId(String nurseId) {
        this.nurseId = nurseId;
    }

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getWardNo() {
        return wardNo;
    }

    public void setWardNo(String wardNo) {
        this.wardNo = wardNo;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "nurseId='" + nurseId + '\'' +
                ", nurseName='" + nurseName + '\'' +
                ", roomNo='" + roomNo + '\'' +
                ", wardNo='" + wardNo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nurse nurse = (Nurse) o;
        return Objects.equals(nurseId, nurse.nurseId) &&
                Objects.equals(nurseName, nurse.nurseName) &&
                Objects.equals(roomNo, nurse.roomNo) &&
                Objects.equals(wardNo, nurse.wardNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nurseId, nurseName, roomNo, wardNo);
    }
}
