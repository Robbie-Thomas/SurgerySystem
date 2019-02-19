import org.jetbrains.annotations.Contract;

import java.util.*;

public class PatientAddress {

    private String houseName, street, city, postcode, county, country;
    private int houseNumber;
    private int id;
    private Date row_Create, row_LastUpdate;


    public PatientAddress() {
    }

    public PatientAddress(String houseName, String street, String city, String postcode, String county, String country, int houseNumber, Date row_Create) {
        this.houseName = houseName;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
        this.county = county;
        this.country = country;
        this.houseNumber = houseNumber;
        this.row_Create = row_Create;
    }

    public Date getRow_Create() {
        return row_Create;
    }

    public void setRow_Create(Date row_Create) {
        this.row_Create = row_Create;
    }

    public Date getRow_LastUpdate() {
        return row_LastUpdate;
    }

    public void setRow_LastUpdate(Date row_LastUpdate) {
        this.row_LastUpdate = row_LastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Contract(value = "null -> false", pure = true)
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;

        PatientAddress obj2 = (PatientAddress) obj;
        if ((this.id == obj2.getId()) && (this.postcode.equals(obj2.getPostcode()))) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int tmp = 0;
        tmp = (id + postcode).hashCode();
        return tmp;
    }
}
