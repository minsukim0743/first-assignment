package com.example.demo.home.controller;

import com.example.demo.user.model.dto.UserDTO;
import com.example.demo.user.model.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {

        this.userService = userService;
    }

    // 
    @GetMapping("/")
    public String home() {

        return "home";
    }

    // userList ajax 요청 메소드
    @GetMapping(value = "/user", produces = "application/text; charset=UTF-8")
    @ResponseBody
    public String selectUserList(HttpServletResponse response){

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();

        List<UserDTO> userList = userService.selectUserList();
        System.out.println("userList 확인 : " + userList);

        response.setCharacterEncoding("UTF-8");

        return gson.toJson(userList);
    }

    @PostMapping("/insert")
    public String insertUser(@RequestParam MultipartFile dbfile){

        return "redirect:/";
    }
}
