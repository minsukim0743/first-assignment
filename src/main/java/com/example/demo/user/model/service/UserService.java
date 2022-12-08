package com.example.demo.user.model.service;

import com.example.demo.user.model.dao.UserMapper;
import com.example.demo.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService implements UserServiceImpl{

    private final UserMapper userMapper;
    public int successCount;
    public int failCount;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> selectUserList() {

        List<UserDTO> userList = userMapper.selectUserList();

        return userList;
    }

    @Override
    public Map<Integer, String> insertUserList(MultipartFile dbFile) throws IOException {

        Map<Integer, String> fail = new HashMap<>();
        InputStream file = dbFile.getInputStream();

        this.successCount = 0;
        this.failCount = 0;

        new BufferedReader(new InputStreamReader(file)).lines().forEach(userInfo -> {
            UserDTO user = new UserDTO(userInfo.split("/"));

            try {

                userMapper.insertUserList(user);
                successCount++;

            }
            catch(Exception e) {

                fail.put(failCount, userInfo);
                failCount++;

            }
        });

        return successCount == failCount ? null : fail;
    }

}
