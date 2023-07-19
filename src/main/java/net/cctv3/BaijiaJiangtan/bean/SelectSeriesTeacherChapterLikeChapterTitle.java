package net.cctv3.BaijiaJiangtan.bean;

import lombok.Data;

@Data
public class SelectSeriesTeacherChapterLikeChapterTitle {
    private int seriesId;
    private int chapterId;
    private String seriesTitle;
    private String teacherName;
    private int teacherId;
    private String chapterTitle;
    private String chapterMessage;
    private String seriesCapture;
    private String chapterCapture;
    private String updateTime;

    public SelectSeriesTeacherChapterLikeChapterTitle() {
    }
}
