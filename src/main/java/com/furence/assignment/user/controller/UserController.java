package com.furence.assignment.user.controller;

import com.furence.assignment.common.Pagenation;
import com.furence.assignment.common.SelectCriteria;
import com.furence.assignment.user.model.dto.UserDTO;
import com.furence.assignment.user.model.dto.UserResponseData;
import com.furence.assignment.user.model.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final Pagenation pagenation;

    @Autowired
    public UserController(UserService userService, Pagenation pagenation) {
        this.userService = userService;
        this.pagenation = pagenation;
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
    public String serverPagingAjax(@PathVariable int pageNo){

        // 테이블 전체 행 개수 조회
        int totalCount = userService.selectTotalCount();

        SelectCriteria selectCriteria = null;
        selectCriteria = pagenation.getSelectCriteria(pageNo, totalCount);

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();

        List<UserDTO> userList = userService.selectUser(selectCriteria);

        System.out.println(userList);
        System.out.println(selectCriteria);

        UserResponseData userResponseData = new UserResponseData(userList, selectCriteria);

        return gson.toJson(userResponseData);
    }

    @GetMapping("/excel")
    public void downloadEwxcel(HttpServletResponse response) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("UserList");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        int rowNo = 0;

        Row headerRow = sheet.createRow(rowNo++);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("PWD");
        headerRow.createCell(2).setCellValue("NAME");
        headerRow.createCell(3).setCellValue("LEVEL");
        headerRow.createCell(4).setCellValue("DESCRIPTION");
        headerRow.createCell(5).setCellValue("REG_DATE");

        List<UserDTO> userList = userService.selectUserList();

        for(UserDTO user : userList){

            Row row = sheet.createRow(rowNo++);
            // 문자로 저장된 숫자 포맷
            int pwd = Integer.parseInt(user.getPwd());
            String level = String.valueOf(user.getLevel());
            String regDate = simpleDateFormat.format(user.getReg_date());

            row.createCell(0).setCellValue(user.getId());
            row.createCell(1).setCellValue(pwd);
            row.createCell(2).setCellValue(user.getName());
            row.createCell(3).setCellValue(level);
            row.createCell(4).setCellValue(user.getDescription());
            row.createCell(5).setCellValue(regDate);
        }

        // 컬럼 사이즈 자동 조절
        for (short i = sheet.getRow(0).getFirstCellNum(),
             end = sheet.getRow(0).getLastCellNum() ; i < end ; i++) {
            sheet.autoSizeColumn(i);
        }

        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=assignment.xlsx");

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
