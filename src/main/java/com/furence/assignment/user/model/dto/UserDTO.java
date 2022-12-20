package com.furence.assignment.user.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Setter
@Getter
@ToString
public class UserDTO {

    private String id;
    private String pwd;
    private String name;
    private char level;
    private String description;
    private Timestamp reg_date;

    public UserDTO() {}

    public UserDTO(String[] array) {

        this.id = array[0];
        this.pwd = array[1];
        this.name = array[2];
        this.level = array[3].charAt(0);
        this.description = array[4];
        this.reg_date = Timestamp.valueOf(array[5]);
    }

}
