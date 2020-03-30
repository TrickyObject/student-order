package mts.domain;

import java.time.LocalDate;

public abstract class Person {

    private String fName;
    private String sName;
    private String pName;
    private LocalDate bd;
    private Adress adress;


    public Person() {

    }

    public Person(String fName, String sName, String pName, LocalDate bd) {
        this.fName = fName;
        this.sName = sName;
        this.pName = pName;
        this.bd = bd;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public LocalDate getBd() {
        return bd;
    }

    public void setBd(LocalDate bd) {
        this.bd = bd;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
