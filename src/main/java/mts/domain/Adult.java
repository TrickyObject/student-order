package mts.domain;

import java.time.LocalDate;

public class Adult extends Person {

    private String passportSerial;
    private String passportNum;
    private LocalDate passportDate;
    private PassportOffice passportOffice;
    private University uni;
    private String studentId;
    private Adress adress;

    public Adult() {
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Adult(String fName, String sName, String pName, LocalDate bd) {
        super(fName, sName, pName, bd);
    }

    public University getUni() {
        return uni;
    }

    public void setUni(University uni) {
        this.uni = uni;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassportSerial() {
        return passportSerial;
    }

    public void setPassportSerial(String passportSerial) {
        this.passportSerial = passportSerial;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    public LocalDate getPassportDate() {
        return passportDate;
    }

    public void setPassportDate(LocalDate passportDate) {
        this.passportDate = passportDate;
    }

    public PassportOffice getPassportOffice() {
        return passportOffice;
    }

    public void setPassportOffice(PassportOffice passportOffice) {
        this.passportOffice = passportOffice;
    }
}
