package net.cctv3.BaijiaJiangtan.bean;

import lombok.Data;

@Data
public class SelectSeriesesByTeacher {
    private String id;
    private String title;
    private String capture;
    private String message;
    private String remark;
    private String debut;
    private String updateTime;
    private int chapters;
    private int durations;

    public SelectSeriesesByTeacher() {
    }

    public SelectSeriesesByTeacher(String id, String title, String capture, String message, String remark, String debut, String updateTime, int chapters, int durations) {
        this.id = id;
        this.title = title;
        this.capture = capture;
        this.message = message;
        this.remark = remark;
        this.debut = debut;
        this.updateTime = updateTime;
        this.chapters = chapters;
        this.durations = durations;
    }
}