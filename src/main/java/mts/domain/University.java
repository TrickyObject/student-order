package mts.domain;

public class University {

    private Long unID;
    private String uniName;

    public University(Long unID, String uniName) {
        this.unID = unID;
        this.uniName = uniName;
    }

    public University() {
    }

    public Long getUnID() {
        return unID;
    }

    public void setUnID(Long unID) {
        this.unID = unID;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    @Override
    public String toString() {
        return "University{" +
                "unID=" + unID +
                ", uniName='" + uniName + '\'' +
                '}';
    }
}
