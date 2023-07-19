package net.cctv3.BaijiaJiangtan.bean;

import lombok.Data;

@Data
public class Series {
    private String id;
    private String teacherId;
    private String title;
    private String capture;
    private String message;
    private String cctv;
    private String remark;
    private String createTime;
    private String updateTime;
    private int debut;
    private int status;

    public Series() {
    }

    public Series(String id, String teacherId, String title, String capture, String message, String cctv, String remark, String createTime, String updateTime, int debut, int status) {
        this.id = id;
        this.teacherId = teacherId;
        this.title = title;
        this.capture = capture;
        this.message = message;
        this.cctv = cctv;
        this.remark = remark;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.debut = debut;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}