package com.example.firstaid.dto;

import java.sql.Timestamp;

public class FileEntity {
    private long fileId;

    /*****
     * 原文件名
     ***/

    private String titleOrig;

    /*****
     * 修改后文件名
     ***/

    private String titleAlter;

    /*****
     * 文件大小
     ***/

    private String size;

    /*****
     * 文件类型
     ***/

    private String type;

    /*****
     * 文件保存路径
     ***/

    private String path;

    /*****
     * 文件上传时间
     ***/

    private Timestamp uploadTime;

    public long getFileId() {
        return fileId;
    }

    public void setFileId(long fileId) {
        this.fileId = fileId;
    }

    public String getTitleOrig() {
        return titleOrig;
    }

    public void setTitleOrig(String titleOrig) {
        this.titleOrig = titleOrig;
    }

    public String getTitleAlter() {
        return titleAlter;
    }

    public void setTitleAlter(String titleAlter) {
        this.titleAlter = titleAlter;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp uploadTime) {
        this.uploadTime = uploadTime;
    }
}
