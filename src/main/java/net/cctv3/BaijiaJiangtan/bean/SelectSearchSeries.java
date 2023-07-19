package net.cctv3.BaijiaJiangtan.bean;

import lombok.Data;

@Data
public class SelectSearchSeries {
    private String id;
    private String title;
    private String message;
    private String capture;
    private String updateTime;
    private String name;
    private String avatar;
    private int chapters;
    private int durations;
    private int clickCount;
    private int collectCount;
    private int likeCount;

    public SelectSearchSeries(String id, String title, String message, String capture, String updateTime, String name, String avatar, int chapters, int durations, int clickCount, int collectCount, int likeCount) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.capture = capture;
        this.updateTime = updateTime;
        this.name = name;
        this.avatar = avatar;
        this.chapters = chapters;
        this.durations = durations;
        this.clickCount = clickCount;
        this.collectCount = collectCount;
        this.likeCount = likeCount;
    }

    public SelectSearchSeries() {
    }
}