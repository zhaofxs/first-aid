package com.example.firstaid.service;

import com.example.firstaid.dto.Result;
import com.example.firstaid.dto.UserEntity;
import com.example.firstaid.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    private int online = 0;

    public UserEntity getUserByAccount(String loginAccount){
        UserEntity userCondition = new UserEntity();
        userCondition.setLoginAccount(loginAccount);
        List<UserEntity> users = userMapper.getUsers(userCondition);
        if(users.size() < 1){
            return  null;
        }
        return users.get(0);
    }

    public Result list(UserEntity userCondition){
        Result result = new Result();
        userCondition.setPageParam();
        List<UserEntity> users = userMapper.getUsers(userCondition);
        result.setCode(0);
        result.setCount(userMapper.getUsersCount(userCondition));
        result.setData(users);
        pushToWeb();
        return result;
    }

    public Map pushToWeb() {
        Map result = new HashMap();
        try {
            online++;
            String message = "当前在限人数" + online;
            WebSocketServer.sendInfo(message);
            result.put("code", 200);
            result.put("msg", "success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
