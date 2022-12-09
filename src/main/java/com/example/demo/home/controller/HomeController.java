package com.example.demo.home.controller;

import com.example.demo.user.model.dto.UserDTO;
import com.example.demo.user.model.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {

        this.userService = userService;
    }

    // 시작 페이지 설정
    @GetMapping("/")
    public String home() {

        return "home";
    }

    // userList ajax 요청 메소드
    @GetMapping(value = "/user", produces = "application/text; charset=UTF-8")
    @ResponseBody
    public String selectUserList(){

        // Gson 날짜 데이터 변경하기
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();

        // userList 조회
        List<UserDTO> userList = userService.selectUserList();
        System.out.println("userList 확인 : " + userList);

        return gson.toJson(userList);
    }

    // DBFILE 확장자 파일 업로드 시 text 내용 DB에 저장
    @PostMapping("/insert")
    public String insertUser(MultipartFile dbFile, RedirectAttributes rttr) throws IOException {

        // 업로드 파일 Service에 전달
        Map<Integer, String> file = userService.insertUserList(dbFile);

        // successCount, failCount 값 넘겨주기
        rttr.addFlashAttribute("successCount", userService.successCount);
        rttr.addFlashAttribute("failCount", userService.failCount);

        // file 업로드 성공 시 file 값 넘겨주기
        if(file != null){

            rttr.addFlashAttribute("file", file);
        }

        return "redirect:/";
    }
}
