package com.example.demo.user.model.service;

import com.example.demo.user.model.dto.UserDTO;

import java.util.List;

public interface UserServiceImpl {
    List<UserDTO> selectUserList();
}
