package mts.domain;

public class RegisterOffice {

    private Long officeID;
    private String officeAreaID;
    private String officeName;

    public RegisterOffice() {
    }

    public RegisterOffice(Long officeID, String officeAreaID, String officeName) {
        this.officeID = officeID;
        this.officeAreaID = officeAreaID;
        this.officeName = officeName;
    }

    public Long getOfficeID() {
        return officeID;
    }

    public void setOfficeID(Long officeID) {
        this.officeID = officeID;
    }

    public String getOfficeAreaID() {
        return officeAreaID;
    }

    public void setOfficeAreaID(String officeAreaID) {
        this.officeAreaID = officeAreaID;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    @Override
    public String toString() {
        return "RegisterOffice{" +
                "officeID=" + officeID +
                ", officeAreaID='" + officeAreaID + '\'' +
                ", officeName='" + officeName + '\'' +
                '}';
    }
}
