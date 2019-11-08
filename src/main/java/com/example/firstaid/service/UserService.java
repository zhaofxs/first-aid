package com.example.firstaid.service;

import com.example.firstaid.dto.Result;
import com.example.firstaid.dto.UserEntity;
import com.example.firstaid.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Result list(UserEntity userCondition){
        Result result = new Result();
        userCondition.setPageParam();
        List<UserEntity> users = userMapper.getUsers(userCondition);
        result.setCode(0);
        result.setCount(userMapper.getUsersCount(userCondition));
        result.setData(users);
        return result;
    }

}
