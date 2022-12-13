package com.furence.assignment.user.model.service;

import com.furence.assignment.user.model.dao.UserMapper;
import com.furence.assignment.user.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    // userList 조회 메소드
    @Override
    public List<UserDTO> selectUserList() {

        // userList 조회
        List<UserDTO> userList = userMapper.selectUserList();

        return userList;
    }

    // userList DB Insert 메소드
    @Override
    @Transactional
    public Map<Integer, String> insertUserList(MultipartFile dbFile) {

        // 실패 정보를 조회하기 위해 Map 객체 생성
        Map<Integer, String> fail = new HashMap<>();

        String fileName = dbFile.getName();
        String ext = fileName.toLowerCase().substring(fileName.lastIndexOf(".") + 1);

        System.out.println("ext : " + ext);

        if(ext.equals("dbfile")) {

            try(
                    // 업로드 된 파일 읽기 위해 InputStream 안에 담기
                    InputStream is = dbFile.getInputStream();
                    // 읽을 파일 변수에 담기
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr))
            {
                // 라인번호 이력 남기기 위하여 AtomicInteger 사용 초기번호 1 설정
                AtomicInteger lineNumber = new AtomicInteger(1);

                // DB Insert 성공 실패 카운트 Controller에서 사용할 수 있게 전역변수로 생성
                this.successCount = 0;
                this.failCount = 0;

                // 업로드한 파일을 한줄씩 읽어 반복문을 돌려 split 을 통하여 배열로 만들어 오버로딩한 생성자 UserDTO에 저장
                br.lines().forEach(userInfo -> { UserDTO user = new UserDTO(userInfo.split("/"));

                    try {

                        // 성공시 user정보를 담아 넘김
                        userMapper.insertUserList(user);
                        // 성공시 count + 1
                        successCount++;
                    } catch (Exception e) {

                        // 실패시 user 정보를 fail 에 담음
                        fail.put(lineNumber.get(), userInfo);
                        // 실패시 count + 1
                        failCount++;
                    } finally {

                        // 메소드 끝날때 현재 값 리턴하고 lineNumber + 1
                        lineNumber.getAndIncrement();
                    }
                });
            } catch (Exception e) {

            }
        }

        return fail;
    }
}
