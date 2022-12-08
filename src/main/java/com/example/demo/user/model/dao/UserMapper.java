package com.example.demo.user.model.dao;

import com.example.demo.user.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<UserDTO> selectUserList();

    void insertUserList(UserDTO user);
}
