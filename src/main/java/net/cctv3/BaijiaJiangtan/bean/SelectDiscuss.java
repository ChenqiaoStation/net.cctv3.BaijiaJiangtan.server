package net.cctv3.BaijiaJiangtan.bean;

import lombok.Data;

@Data
public class SelectDiscuss {
    private String id;
    private String customerId;
    private String time;
    private String substance;
    private String avatar;
    private String name;

    public SelectDiscuss() {
    }

    public SelectDiscuss(String id, String customerId, String time, String substance, String avatar, String name) {
        this.id = id;
        this.customerId = customerId;
        this.time = time;
        this.substance = substance;
        this.avatar = avatar;
        this.name = name;
    }
}