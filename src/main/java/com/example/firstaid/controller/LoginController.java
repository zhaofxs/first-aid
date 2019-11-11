package com.example.firstaid.controller;

import com.example.firstaid.dto.UserEntity;
import com.example.firstaid.service.UserService;
import com.example.firstaid.service.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    private int online = 0;

    @Autowired
    private UserService userService;

    @RequestMapping(value="/index")
    public String index(){
        return "layuiAdmin";
    }

    @RequestMapping(value="/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping(value="/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String loginAccount = request.getParameter("loginAccount");
        String password = request.getParameter("password");
        UserEntity userEntity = userService.getUserByAccount(loginAccount);
        if(userEntity != null && "password".equals(userEntity.getPassword())){
            //得到session对象
            HttpSession session = request.getSession();
            session.setAttribute("loginAccount", loginAccount);
            userLogin();
            return "layuiAdmin";
        }
        return "login";
    }


    public Map userLogin() {
        Map result = new HashMap();
        try {
            online++;
            String message = "当前在限人数:" + online;
            WebSocketServer.sendInfo(message);
            result.put("code", 200);
            result.put("msg", "success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
