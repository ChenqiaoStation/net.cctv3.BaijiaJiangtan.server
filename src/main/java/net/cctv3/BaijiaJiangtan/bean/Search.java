package net.cctv3.BaijiaJiangtan.bean;

import lombok.Data;

@Data
public class Search {
    private String id;
    private String customerId;
    private String keywords;
    private String time;
    private int status;

    public Search() {
    }

    public Search(String id, String customerId, String keywords, String time, int status) {
        this.id = id;
        this.customerId = customerId;
        this.keywords = keywords;
        this.time = time;
        this.status = status;
    }
}