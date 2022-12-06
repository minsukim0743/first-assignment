package com.example.demo.user.model.service;

import com.example.demo.user.model.dao.UserMapper;
import com.example.demo.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceImpl{

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> selectUserList() {

        List<UserDTO> userList = userMapper.selectUserList();

        return userList;
    }
}
