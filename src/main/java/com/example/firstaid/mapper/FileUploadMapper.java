package com.example.firstaid.mapper;

import com.example.firstaid.dto.FileEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileUploadMapper {

     List getFile();
     void insert(FileEntity fileEntity);
}
