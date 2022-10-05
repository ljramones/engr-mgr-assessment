package com.spothero.engmgr.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WorkedHoursDTO {

    /**
     * the user id for this record
     */

    private int userId;

    /**
     * the date for this time card
     * we want it to follow this format "2021-01-04"
     */
    private String date;

    /**
     * the hour worked in this time card
     */
    private float hours;

}
