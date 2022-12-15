package com.furence.assignment.user.controller;

import com.furence.assignment.common.Pagenation;
import com.furence.assignment.common.SelectCriteria;
import com.furence.assignment.user.model.dto.UserDTO;
import com.furence.assignment.user.model.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/serverPaging")
    public String serverPaging() {

        return "serverPaging";
    }

    @GetMapping("/dhtmlxPaging")
    public String dhtmlxPaging(Model model) {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();

        List<UserDTO> userList = userService.selectUserList();

        model.addAttribute("userList", gson.toJson(userList));

        return "dhtmlxPaging";
    }

    @GetMapping(value = "/user/{pageNo}", produces = "application/text; charset=UTF-8")
    @ResponseBody
    public Object serverPagingAjax(@PathVariable int pageNo, HttpServletRequest request){

        String currentPage = request.getParameter("currentPage");

        if (currentPage != null && !"".equals(currentPage)) {
            pageNo = Integer.parseInt(currentPage);
        }

        // 테이블 전체 행 개수 조회
        int totalCount = userService.selectTotalCount();

        SelectCriteria selectCriteria = null;
        selectCriteria = Pagenation.getSelectCriteria(pageNo, totalCount);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();

        List<UserDTO> userList = userService.selectUser(selectCriteria);

        HashMap<String, Object> pageData = new HashMap<>();

        System.out.println("userList : " + userList);
        System.out.println("selectCriteria : " + selectCriteria);

        pageData.put("userList", userList);
        pageData.put("selectCriteria", selectCriteria);

        return gson.toJson(pageData);
    }
}
