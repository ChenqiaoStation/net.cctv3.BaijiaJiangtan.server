package net.cctv3.BaijiaJiangtan.bean;

import lombok.Data;

@Data
public class SelectSearchTeacher {
    private String id;
    private String name;
    private String avatar;
    private String title;
    private String message;
    private int status;
    private int clickCount;
    private int collectCount;
    private int likeCount;

    public SelectSearchTeacher(String id, String name, String avatar, String title, String message, int status, int clickCount, int collectCount, int likeCount) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.title = title;
        this.message = message;
        this.status = status;
        this.clickCount = clickCount;
        this.collectCount = collectCount;
        this.likeCount = likeCount;
    }

    public SelectSearchTeacher() {
    }
}
