package com.example.demo.user.model.dao;

import com.example.demo.user.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    // userList 조회
    List<UserDTO> selectUserList();

    // user 등록
    void insertUserList(UserDTO user);
}
