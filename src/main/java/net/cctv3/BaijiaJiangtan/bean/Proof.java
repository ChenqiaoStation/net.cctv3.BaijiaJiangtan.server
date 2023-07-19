package net.cctv3.BaijiaJiangtan.bean;

import lombok.Data;

@Data
public class Proof {
    private String id;
    private String teacherId;
    private String seriesId;
    private String chapterId;
    private String dataIn;
    private String dataOut;
    private String time;

    public Proof() {

    }

    public Proof(String id, String teacherId, String seriesId, String chapterId, String dataIn, String dataOut, String time) {
        this.id = id;
        this.teacherId = teacherId;
        this.seriesId = seriesId;
        this.chapterId = chapterId;
        this.dataIn = dataIn;
        this.dataOut = dataOut;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}