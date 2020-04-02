package mts.domain;

public class Adress {

    private String postCode;
    private Street street;
    private String building;
    private String apartment;

    public Adress() {
    }

    public Adress(String postCode, Street street, String building, String apartment) {
        this.postCode = postCode;
        this.street = street;
        this.building = building;
        this.apartment = apartment;
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

    @Override
    public String toString() {
        return "Adress{" +
                "postCode='" + postCode + '\'' +
                ", street=" + street +
                ", building='" + building + '\'' +
                ", apartment='" + apartment + '\'' +
                '}';
    }
}
