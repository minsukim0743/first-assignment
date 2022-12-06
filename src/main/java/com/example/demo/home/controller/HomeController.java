package com.example.demo.home.controller;

import com.example.demo.user.model.dto.UserDTO;
import com.example.demo.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model) {

        List<UserDTO> userList = userService.selectUserList();

        System.out.println("userList 확인 : " + userList);
        model.addAttribute("userList", userList);

        return "redirect:home";
    }
}
