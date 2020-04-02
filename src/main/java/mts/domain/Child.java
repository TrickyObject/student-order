package mts.domain;

import java.time.LocalDate;

public class Child extends Person {

    private String certNum;
    private LocalDate issueDate;
    private RegisterOffice issueDep;

    public Child(String fName, String sName, String pName, LocalDate bd) {
        super(fName, sName, pName, bd);

    }


    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public RegisterOffice getIssueDep() {
        return issueDep;
    }

    public void setIssueDep(RegisterOffice issueDep) {
        this.issueDep = issueDep;
    }

    @Override
    public String toString() {
        return "Child{" +
                "certNum='" + certNum + '\'' +
                ", issueDate=" + issueDate +
                ", issueDep=" + issueDep +
                "} " + super.toString();
    }
}
