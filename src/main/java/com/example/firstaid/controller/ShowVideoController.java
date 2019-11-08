package com.example.firstaid.controller;

import com.example.firstaid.dto.FileEntity;
import com.example.firstaid.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/show")
public class ShowVideoController {

    @Autowired
    FileService fileService;
    @RequestMapping(value="/video")
    public String video(){
        return "video";
    }

   /* @RequestMapping(value="/goUpload")
    public String goUpload(){
        List<FileEntity> list = fileService.getFile();
        return "upload";
    }*/

}
