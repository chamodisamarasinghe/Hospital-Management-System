package tm;

public class NurseTM {
    private String nurseId;
    private String nurseName;
    private String roomNo;
    private String wardNo;

    public NurseTM() {
    }

    public NurseTM(String nurseId, String nurseName, String roomNo, String wardNo) {
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
        return "NurseTM{" +
                "nurseId='" + nurseId + '\'' +
                ", nurseName='" + nurseName + '\'' +
                ", roomNo='" + roomNo + '\'' +
                ", wardNo='" + wardNo + '\'' +
                '}';
    }
}
