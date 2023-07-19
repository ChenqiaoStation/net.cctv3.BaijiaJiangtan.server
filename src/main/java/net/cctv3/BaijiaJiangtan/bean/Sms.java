package net.cctv3.BaijiaJiangtan.bean;

import lombok.Data;

@Data
public class Sms {
    private String id;
    private String mobile;
    private String code;
    private String time;

    public Sms() {
    }

    public Sms(String id, String mobile, String code, String time) {
        this.id = id;
        this.mobile = mobile;
        this.code = code;
        this.time = time;
    }
}
