package com.furence.assignment.user.model.dto;

import java.sql.Timestamp;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getLevel() {
        return level;
    }

    public void setLevel(char level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getReg_date() {
        return reg_date;
    }

    public void setReg_date(Timestamp reg_date) {
        this.reg_date = reg_date;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", description='" + description + '\'' +
                ", reg_date=" + reg_date +
                '}';
    }
}
