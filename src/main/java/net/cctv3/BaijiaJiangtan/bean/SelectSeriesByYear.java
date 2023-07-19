package net.cctv3.BaijiaJiangtan.bean;

import lombok.Data;

@Data
public class SelectSeriesByYear {
    private String id;
    private String title;
    private String capture;
    private String message;
    private int clickCount;
    private int likeCount;

    public SelectSeriesByYear(String id, String title, String capture, String message, int clickCount, int likeCount) {
        this.id = id;
        this.title = title;
        this.capture = capture;
        this.message = message;
        this.clickCount = clickCount;
        this.likeCount = likeCount;
    }

    public SelectSeriesByYear() {
    }
}