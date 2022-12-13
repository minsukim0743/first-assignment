package com.furence.assignment.user.model.service;

import com.furence.assignment.user.model.dto.InsertData;
import com.furence.assignment.user.model.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserServiceImpl {
    List<UserDTO> selectUserList();

    InsertData insertUserList(MultipartFile dbFile) throws IOException;
}
