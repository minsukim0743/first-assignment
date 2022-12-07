package com.example.demo.user.model.service;

import com.example.demo.user.model.dto.UserDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserServiceImpl {
    List<UserDTO> selectUserList();

    Map<Integer, String> insertUserList(MultipartFile dbFile) throws IOException;
}
