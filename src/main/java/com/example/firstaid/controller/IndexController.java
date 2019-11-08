package com.example.firstaid.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class IndexController {

    @RequestMapping(value="/index")
    public String index(){
        return "layuiAdmin";
    }

    @RequestMapping(value="/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value="/toLogin")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        if("test".equals(userName) && "123".equals(password)){
            //得到session对象
            HttpSession session = request.getSession();
            session.setAttribute("userName", userName);
            return "layuiAdmin";
        }
        return "login";
    }

}
