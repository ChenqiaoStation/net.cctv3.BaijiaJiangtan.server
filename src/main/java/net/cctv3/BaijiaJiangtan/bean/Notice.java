package net.cctv3.BaijiaJiangtan.bean;

import lombok.Data;

@Data
public class Notice {
    private String id;
    private String title;
    private String message;
    private String remark;
    private String web;
    private String createTime;
    private String updateTime;
    private int status;

    public Notice(String id, String title, String message, String remark, String web, String createTime, String updateTime, int status) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.remark = remark;
        this.web = web;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
    }

    public Notice() {
    }
}