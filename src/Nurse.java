import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.jetbrains.annotations.Contract;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@NamedQueries(
        @NamedQuery(
                name = "findAppointmentByPatientName",
                query = "from Patient p where p.firstName = :firstName AND p.lastName = :lastName"
        )
)



@Entity
@Table(name = "nurse")
public class Nurse{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Nurse_Id")
    private Integer id;

    @Column(name = "First_Name")
    private String  firstName;

    @Column(name = "Last_Name")
    private String  lastName;

    @Column( name="Is_Male")
    private boolean isMale;

    @Column(name = "Specialist_Area")
    private String  specialistArea;

    @Column(name = "Monday_AM")
    private Boolean MondayAM;

    @Column(name = "Monday_PM")
    private Boolean MondayPM;

    @Column(name = "Tuesday_AM")
    private Boolean TuesdayAM;

    @Column(name = "Tuesday_PM")
    private Boolean TuesdayPM;

    @Column(name = "Wednesday_AM")
    private Boolean WednesdayAM;

    @Column(name = "Wednesday_PM")
    private Boolean WednesdayPM;

    @Column(name = "Thursday_AM")
    private Boolean ThursdayAM;

    @Column(name = "Thursday_PM")
    private Boolean ThursdayPM;

    @Column(name = "Friday_AM")
    private Boolean FridayAM;

    @Column(name= "Friday_PM")
    private Boolean FridayPM;

    @Column(name = "Saturday_AM")
    private Boolean SaturdayAM;

    @Column(name = "Saturday_PM")
    private Boolean SaturdayPM;

    @Column(name= "Sunday_AM")
    private Boolean SundayAM;

    @Column(name = "Sunday_PM")
    private Boolean SundayPM;

    @Column (name = "Row_Create")
    @CreationTimestamp
    private LocalDateTime createDateTime;

    @Column (name = "Row_LastUpdate")
    @UpdateTimestamp
    private LocalDateTime updateDateTime;

    public Nurse() {
    }

    public Nurse(String firstName, String lastName, String specialistArea, Boolean isMale, Boolean mondayAM, Boolean mondayPM, Boolean tuesdayAM, Boolean tuesdayPM, Boolean wednesdayAM, Boolean wednesdayPM, Boolean thursdayAM, Boolean thursdayPM, Boolean fridayAM, Boolean fridayPM, Boolean saturdayAM, Boolean saturdayPM, Boolean sundayAM, Boolean sundayPM) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialistArea = specialistArea;
        this.isMale = isMale;
        MondayAM = mondayAM;
        MondayPM = mondayPM;
        TuesdayAM = tuesdayAM;
        TuesdayPM = tuesdayPM;
        WednesdayAM = wednesdayAM;
        WednesdayPM = wednesdayPM;
        ThursdayAM = thursdayAM;
        ThursdayPM = thursdayPM;
        FridayAM = fridayAM;
        FridayPM = fridayPM;
        SaturdayAM = saturdayAM;
        SaturdayPM = saturdayPM;
        SundayAM = sundayAM;
        SundayPM = sundayPM;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpecialistArea() {
        return specialistArea;
    }

    public void setSpecialistArea(String specialistArea) {
        this.specialistArea = specialistArea;
    }

    public Boolean getMondayAM() {
        return MondayAM;
    }

    public void setMondayAM(Boolean mondayAM) {
        MondayAM = mondayAM;
    }

    public Boolean getMondayPM() {
        return MondayPM;
    }

    public void setMondayPM(Boolean mondayPM) {
        MondayPM = mondayPM;
    }

    public Boolean getTuesdayAM() {
        return TuesdayAM;
    }

    public void setTuesdayAM(Boolean tuesdayAM) {
        TuesdayAM = tuesdayAM;
    }

    public Boolean getTuesdayPM() {
        return TuesdayPM;
    }

    public void setTuesdayPM(Boolean tuesdayPM) {
        TuesdayPM = tuesdayPM;
    }

    public Boolean getWednesdayAM() {
        return WednesdayAM;
    }

    public void setWednesdayAM(Boolean wednesdayAM) {
        WednesdayAM = wednesdayAM;
    }

    public Boolean getWednesdayPM() {
        return WednesdayPM;
    }

    public void setWednesdayPM(Boolean wednesdayPM) {
        WednesdayPM = wednesdayPM;
    }

    public Boolean getThursdayAM() {
        return ThursdayAM;
    }

    public void setThursdayAM(Boolean thursdayAM) {
        ThursdayAM = thursdayAM;
    }

    public Boolean getThursdayPM() {
        return ThursdayPM;
    }

    public void setThursdayPM(Boolean thursdayPM) {
        ThursdayPM = thursdayPM;
    }

    public Boolean getFridayAM() {
        return FridayAM;
    }

    public void setFridayAM(Boolean fridayAM) {
        FridayAM = fridayAM;
    }

    public Boolean getFridayPM() {
        return FridayPM;
    }

    public void setFridayPM(Boolean fridayPM) {
        FridayPM = fridayPM;
    }

    public Boolean getSaturdayAM() {
        return SaturdayAM;
    }

    public void setSaturdayAM(Boolean saturdayAM) {
        SaturdayAM = saturdayAM;
    }

    public Boolean getSaturdayPM() {
        return SaturdayPM;
    }

    public void setSaturdayPM(Boolean saturdayPM) {
        SaturdayPM = saturdayPM;
    }

    public Boolean getSundayAM() {
        return SundayAM;
    }

    public void setSundayAM(Boolean sundayAM) {
        SundayAM = sundayAM;
    }

    public Boolean getSundayPM() {
        return SundayPM;
    }

    public void setSundayPM(Boolean sundayPM) {
        SundayPM = sundayPM;
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nurse)) return false;
        Nurse nurse = (Nurse) o;
        return id == nurse.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
