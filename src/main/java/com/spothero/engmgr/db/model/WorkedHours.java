package com.spothero.engmgr.db.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "worked_hours")
@IdClass(WorkedHoursId.class)
public class WorkedHours implements Serializable {

    /**
     * the user id for this record
     */
    @Id
    @Column(name = "user_id")
    private int userId;

    /**
     * the date for this time card
     */
    @Id
    @Column(name = "date")
    private Date date;

    /**
     * the hour worked in this time card
     */
    @Column(name = "hours")
    private float hours;

    // I didn't need this reverse FK for the example, but I left it here as an example of how
//    @ManyToOne(fetch = FetchType.EAGER, optional = true)
//    @JoinColumn(name = "id")
//    private User user;

}
