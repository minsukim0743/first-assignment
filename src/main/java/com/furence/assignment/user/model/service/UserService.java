package com.furence.assignment.user.model.service;

import com.furence.assignment.common.SelectCriteria;
import com.furence.assignment.user.model.dao.UserMapper;
import com.furence.assignment.user.model.dto.InsertData;
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
public class UserService implements UserServiceImpl {

    private final UserMapper userMapper;
    final String DBFILE = "dbfile";

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // userList 조회 메소드
    @Override
    public List<UserDTO> selectUserList() {

        // userList 조회
        List<UserDTO> userList = userMapper.selectUserList();

        System.out.println("userList : " + userList);

        return userList;
    }

    // userList DB Insert 메소드
    @Override
    @Transactional
    public InsertData insertUserList(MultipartFile dbFile) {

        // 실패 정보를 조회하기 위해 Map 객체 생성
        Map<Integer, String> fail = new HashMap<>();

        // 성공, 실패 개수 변수
        AtomicInteger successCount = new AtomicInteger();
        AtomicInteger failCount = new AtomicInteger();
        // 실패 라인번호 변수
        AtomicInteger failLineNumber = new AtomicInteger(1);

        // 업로드 파일명을 읽어 확장자 체크
        String fileName = dbFile.getName();
        String ext = fileName.toLowerCase().substring(fileName.lastIndexOf(".") + 1);

        System.out.println("확장자 체크 : " + ext);

        if (ext.equals(DBFILE)) {

            try (
                    // 업로드 된 파일 읽기 위해 InputStream 안에 담기
                    InputStream is = dbFile.getInputStream();
                    // 읽을 파일 변수에 담기
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr)) {

                // 업로드한 파일을 한줄씩 읽어 반복문을 돌려 split 을 통하여 배열로 만들어 오버로딩한 생성자 UserDTO에 저장
                br.lines().forEach(userInfo -> {
                    UserDTO user = new UserDTO(userInfo.split("/"));

                    try {

                        // 성공시 user 정보를 담아 넘김
                        userMapper.insertUserList(user);
                        // 성공시 count + 1
                        successCount.getAndIncrement();
                    } catch (Exception e) {

                        // 실패시 user 정보를 fail 에 담음
                        fail.put(failLineNumber.get(), userInfo);
                        // 실패시 count + 1
                        failCount.getAndIncrement();
                    } finally {

                        // 메소드 끝날때 현재 값 리턴하고 + 1
                        failLineNumber.getAndIncrement();
                    }
                });
            } catch (Exception e) {

            }
        }

        return new InsertData(fail, successCount, failCount);
    }

    // 테이블 전체 행 개수 조회
    @Override
    public int selectTotalCount() {

        return userMapper.selectTotalCount();
    }

    // Server Paging 조회
    @Override
    public List<UserDTO> selectUser(SelectCriteria selectCriteria) {

        return userMapper.selectUser(selectCriteria);
    }


}
