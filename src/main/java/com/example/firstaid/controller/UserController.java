package com.example.firstaid.controller;

import com.example.firstaid.dto.Result;
import com.example.firstaid.dto.UserEntity;
import com.example.firstaid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/showUser")
    public String video(){
        return "users";
    }

    @RequestMapping(value="/list")
    @ResponseBody
    public Object list(UserEntity userCondition){
        return userService.list(userCondition);
    }

}
