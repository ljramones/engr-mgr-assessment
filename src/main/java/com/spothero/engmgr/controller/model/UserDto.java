package com.spothero.engmgr.controller.model;

import com.spothero.engmgr.db.model.Users;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * the Data Transfer object
 * meant to hold a limited set of returned data to the client that came form the DB
 */
@Data
@AllArgsConstructor
public class UserDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    public UserDto() {
    }

    public UserDto(Users users) {
        this.id = users.getId();
        this.firstName = users.getFirstName();
        this.lastName = users.getLastName();
        this.email = users.getEmail();
    }
}
