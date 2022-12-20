package com.furence.assignment.user.model.dto;

import com.furence.assignment.common.SelectCriteria;
import java.util.List;

public class UserResponseData extends ResponseData{

    List<UserDTO> userList;
    SelectCriteria selectCriteria;

    public UserResponseData(boolean isSuccess, String message) {
        super(isSuccess, message);
    }

    public UserResponseData(List<UserDTO> userList, SelectCriteria selectCriteria){

        super();
        this.userList = userList;
        this.selectCriteria = selectCriteria;
    }

    public List<UserDTO> getUserList() {
        return userList;
    }

    public void setUserList(List<UserDTO> userList) {
        this.userList = userList;
    }

    public SelectCriteria getSelectCriteria() {
        return selectCriteria;
    }

    public void setSelectCriteria(SelectCriteria selectCriteria) {
        this.selectCriteria = selectCriteria;
    }

    @Override
    public String toString() {
        return "UserResponseData{" +
                "userList=" + userList +
                ", selectCriteria=" + selectCriteria +
                ", isSuccess=" + isSuccess +
                ", message='" + message + '\'' +
                '}';
    }
}

