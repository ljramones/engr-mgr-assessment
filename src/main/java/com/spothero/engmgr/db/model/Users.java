package com.spothero.engmgr.db.model;

import com.spothero.engmgr.controller.model.UserDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name = "users",
        indexes = {
                @Index(columnList = "email")}
)
public class Users implements Serializable {

    /**
     * the primary key for this row
     */
    @Id
    private Integer id;

    /**
     * the manager id
     */
    @Column(name = "manager_id")
    private Integer managerId;

    /**
     * user's first name
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * user's last name
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * user's email address
     */
    @Column(name = "email")
    private String email;

    /**
     * Is this user active?
     */
    @Column(name = "active")
    private boolean active;

    // I didn't need this foreign key for the example, but I leave it here as an example of how it would be done
//    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Set<WorkedHours> workedHours = new HashSet<>();

    /**
     * used to map to a corresponding DTO (data transfer object) object
     *
     * @return the DTO object
     */
    public UserDto toUserDto() {
        return new UserDto(this);
    }



}
