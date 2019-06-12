import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.Contract;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;


@NamedQueries(
        value = {
                @NamedQuery(
                        name = "findAddressByName",
                        query = "select t from Patient_Address t \n" +
                                "join t.patient p \n" +
                                "where p.firstName = :firstName AND p.lastName = :lastName"
                )
        }
)


@Entity
@Table(name = "Patient_Address")
@Access(value=AccessType.FIELD)
public class Patient_Address {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "PatientAddress_Id")
    private Integer id;

    @ManyToOne
    @JoinColumn (name = "Patient_Id", nullable = false)
    private Patient patient;

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


    public Patient_Address() {
    }

    /**
     * @param patient
     * @param houseName
     * @param houseNumber
     * @param street
     * @param city
     * @param postcode
     * @param county
     * @param country
     */
    public Patient_Address(Patient patient, String houseName, String houseNumber, String street, String city, String postcode, String county, String country) {
        this.patient = patient;
        this.houseName = houseName;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
        this.county = county;
        this.country = country;
        this.houseNumber = houseNumber;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getHouseName() {
        return houseName;
    }

    /**
     *
     * @param houseName
     */
    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    /**
     *
     * @return
     */
    public String getStreet() {
        return street;
    }

    /**
     *
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     *
     * @param postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     *
     * @return
     */
    public String getCounty() {
        return county;
    }

    /**
     *
     * @param county
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     *
     * @return
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     *
     * @return
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     *
     * @param patientId
     */
    public void setPatient(Patient patientId) {
        this.patient = patientId;
    }

    /**
     *
     * @param houseNumber
     */
    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    /**
     *
     * @param o
     * @return
     */
    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient_Address)) return false;
        Patient_Address that = (Patient_Address) o;
        return houseNumber == that.houseNumber &&
                id.equals(that.id) &&
                Objects.equals(houseName, that.houseName) &&
                Objects.equals(postcode, that.postcode);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
