package mts.domain;

public class Adress {

    // скорее всего будет провал в расширении
    // 10:00да она и грязная ещё сильно

    private String postCode;
    private Street street;
    private String building;
    private String extension;
    private String apartment;

    public Adress() {
    }

    public Adress(String postCode, Street street, String building, String extension, String apartment) {
        this.postCode = postCode;
        this.street = street;
        this.building = building;
        this.extension = extension;
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "postCode='" + postCode + '\'' +
                ", street=" + street +
                ", building='" + building + '\'' +
                ", extension='" + extension + '\'' +
                ", apartment='" + apartment + '\'' +
                '}';
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }

}
