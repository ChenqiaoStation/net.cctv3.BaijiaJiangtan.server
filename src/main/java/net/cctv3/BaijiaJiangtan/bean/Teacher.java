package net.cctv3.BaijiaJiangtan.bean;

import lombok.Data;

@Data
public class Teacher {
    private String id;
    private String name;
    private String avatar;
    private String title;
    private String message;
    private String createTime;
    private String updateTime;
    private int status;

    public Teacher(String id, String name, String avatar, String title, String message, String createTime, String updateTime, int status) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.title = title;
        this.message = message;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.status = status;
    }

    public Teacher() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getters and setters
}