package net.cctv3.BaijiaJiangtan.bean;

import lombok.Data;

@Data
public class Discuss {
    private String id;
    private String batteredId;
    private String parentId;
    private String customerId;
    private String substance;
    private String time;
    private int status;

    public Discuss() {}

    public Discuss(String id, String batteredId, String parentId, String customerId, String substance, String time, int status) {
        this.id = id;
        this.batteredId = batteredId;
        this.parentId = parentId;
        this.customerId = customerId;
        this.substance = substance;
        this.time = time;
        this.status = status;
    }
}