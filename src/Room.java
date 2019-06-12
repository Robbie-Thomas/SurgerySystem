import org.jetbrains.annotations.Contract;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Room_Id")
    private Integer id;


    @Column(name = "Name")
    private String roomName;

    @Column(name = "Number")
    private String roomNumber;

    public Room() {
    }

    /**
     * @param roomName
     * @param roomNumber
     */
    public Room(String roomName, String roomNumber) {
        this.roomName = roomName;
        this.roomNumber = roomNumber;
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
    public String getRoomName() {
        return roomName;
    }

    /**
     *
     * @param roomName
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     *
     * @return
     */
    public String getRoomNumber() {
        return roomNumber;
    }

    /**
     *
     * @param roomNumber
     */
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
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
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return id.equals(room.id);
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
