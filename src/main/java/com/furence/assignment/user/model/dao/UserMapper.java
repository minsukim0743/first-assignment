package com.furence.assignment.user.model.dao;

import com.furence.assignment.common.SelectCriteria;
import com.furence.assignment.user.model.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    // userList 조회
    List<UserDTO> selectUserList();

    // user 등록
    void insertUserList(UserDTO user);

    // 테이블 전체 행 개수 조회
    int selectTotalCount();

    // Server Paging 조회
    List<UserDTO> selectUser(SelectCriteria selectCriteria);

}
