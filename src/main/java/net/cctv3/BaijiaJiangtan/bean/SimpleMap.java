package net.cctv3.BaijiaJiangtan.bean;

import lombok.Data;

@Data
public class SimpleMap {
    private String name;
    private int value;

    public SimpleMap(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public SimpleMap() {
    }
}
