package net.cctv3.BaijiaJiangtan.bean;

import lombok.Data;

@Data
public class Chapter {
    private String id;
    private String seriesId;
    private String cctvId;
    private String title;
    private String capture;
    private String message;
    private String cctvM3u8;
    private String cctvWeb;
    private String cctvMp4Intervals;
    private String cctvMp4Urls;
    private String cctvMp3;
    private int duration;
    private String createTime;
    private String spiderTime;
    private String updateTime;
    private int status;

    public Chapter() {
    }

    public Chapter(String id, String seriesId, String cctvId, String title, String capture, String message, String cctvM3u8, String cctvWeb, String cctvMp4Intervals, String cctvMp4Urls, String cctvMp3, int duration, String createTime, String updateTime, String spiderTime, int status) {
        this.id = id;
        this.seriesId = seriesId;
        this.cctvId = cctvId;
        this.title = title;
        this.capture = capture;
        this.message = message;
        this.cctvM3u8 = cctvM3u8;
        this.cctvWeb = cctvWeb;
        this.cctvMp4Intervals = cctvMp4Intervals;
        this.cctvMp4Urls = cctvMp4Urls;
        this.cctvMp3 = cctvMp3;
        this.duration = duration;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.spiderTime = spiderTime;
        this.status = status;
    }
}