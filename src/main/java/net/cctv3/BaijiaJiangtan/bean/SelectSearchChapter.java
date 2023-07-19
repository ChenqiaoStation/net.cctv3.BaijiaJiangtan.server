package net.cctv3.BaijiaJiangtan.bean;

import lombok.Data;

@Data
public class SelectSearchChapter {
    private String seriesId;
    private String chapterId;
    private String seriesCapture;
    private String chapterCapture;
    private String seriesTitle;
    private String seriesMessage;
    private String chapterTitle;
    private String chapterMessage;
    private int duration;
    private String updateTime;
    private String createTime;
    private int clickCount;
    private int collectCount;
    private int likeCount;

    public SelectSearchChapter(String seriesId, String chapterId, String seriesCapture, String chapterCapture, String seriesTitle, String seriesMessage, String chapterTitle, String chapterMessage, int duration, String updateTime, String createTime, int clickCount, int collectCount, int likeCount) {
        this.seriesId = seriesId;
        this.chapterId = chapterId;
        this.seriesCapture = seriesCapture;
        this.chapterCapture = chapterCapture;
        this.seriesTitle = seriesTitle;
        this.seriesMessage = seriesMessage;
        this.chapterTitle = chapterTitle;
        this.chapterMessage = chapterMessage;
        this.duration = duration;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.clickCount = clickCount;
        this.collectCount = collectCount;
        this.likeCount = likeCount;
    }

    public SelectSearchChapter() {
    }
}