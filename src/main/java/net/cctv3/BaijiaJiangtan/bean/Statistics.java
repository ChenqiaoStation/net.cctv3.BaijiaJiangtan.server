package net.cctv3.BaijiaJiangtan.bean;

import lombok.Data;

@Data
public class Statistics {
    private String id;
    private String customerId;
    private String batteredId;
    private String intent;
    private String time;

    public Statistics() {
    }

    public Statistics(String id, String customerId, String batteredId, String intent, String time) {
        this.id = id;
        this.customerId = customerId;
        this.batteredId = batteredId;
        this.intent = intent;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}