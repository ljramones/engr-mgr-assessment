package com.spothero.engmgr.db.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WorkedHoursId implements Serializable {

    /**
     * the user id for this record
     */
    private int userId;

    /**
     * the date for this time card
     */
    private Date date;

}
