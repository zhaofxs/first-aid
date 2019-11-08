package com.example.firstaid.dto;

public class BaseEntity {
    private int page;
    private int limit;
    private int startNum;

    public void setPageParam(){
        this.startNum = (this.page - 1) * this.limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

}
