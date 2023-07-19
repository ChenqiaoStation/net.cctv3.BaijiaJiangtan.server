package net.cctv3.BaijiaJiangtan.bean;

import lombok.Data;

@Data
public class SelectTeacherAndCount {
    private String id;
    private String name;
    private String avatar;
    private String title;
    private String message;
    private int clickCount;
    private int series;
    private int durations;
    private int chapters;
    private int status;

    public SelectTeacherAndCount() {
    }

    public SelectTeacherAndCount(String id, String name, String avatar, String title, String message, int clickCount, int series, int durations, int chapters, int status) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.title = title;
        this.message = message;
        this.clickCount = clickCount;
        this.series = series;
        this.durations = durations;
        this.chapters = chapters;
        this.status = status;
    }
}