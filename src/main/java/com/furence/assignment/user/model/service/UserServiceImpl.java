package com.furence.assignment.user.model.service;

import com.furence.assignment.common.SelectCriteria;
import com.furence.assignment.user.model.dto.InsertData;
import com.furence.assignment.user.model.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserServiceImpl {

    // userList 조회
    List<UserDTO> selectUserList();

    // user 등록
    InsertData insertUserList(MultipartFile dbFile) throws IOException;

    // 테이블 전체 행 개수 조회
    int selectTotalCount();

    // Server Paging 조회
    List<UserDTO> selectUser(SelectCriteria selectCriteria);

}
