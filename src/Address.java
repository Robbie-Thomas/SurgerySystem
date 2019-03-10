import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.Contract;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;


@Entity
@Table(name = "address")
@Access(value=AccessType.FIELD)
public class PatientAddress {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "PatientAddress_Id")
    private Integer id;

    @ManyToOne
    @JoinColumn (name = "Patient_Id")
    private int patientId;

    @Column(name = "House_Name")
    private String houseName;

    @Column(name = "House_Number")
    private String houseNumber;

    @Column(name = "Street")
    private String street;

    @Column(name = "City")
    private String city;

    @Column(name = "Postcode")
    private String postcode;

    @Column(name = "County")
    private String county;

    @Column(name = "Country")
    private String country;



    @Column (name = "Row_Create")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column (name = "Row_LastUpdate")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;


    public PatientAddress() {
    }

    public PatientAddress(int patientId,String houseName, String street, String city, String postcode, String county, String country, String houseNumber) {
        this.patientId = patientId;
        this.houseName = houseName;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
        this.county = county;
        this.country = country;
        this.houseNumber = houseNumber;
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getHouseNumber() {
        return houseNumber;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PatientAddress)) return false;
        PatientAddress that = (PatientAddress) o;
        return houseNumber == that.houseNumber &&
                id == that.id &&
                Objects.equals(houseName, that.houseName) &&
                Objects.equals(postcode, that.postcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
