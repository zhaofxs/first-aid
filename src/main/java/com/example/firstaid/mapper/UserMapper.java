package com.example.firstaid.mapper;

import com.example.firstaid.dto.FileEntity;
import com.example.firstaid.dto.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper {

     List<UserEntity> getUsers(UserEntity userCondition);

     int getUsersCount(UserEntity userCondition);
}
