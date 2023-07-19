package net.cctv3.BaijiaJiangtan.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Customer {
    private String id;
    private String uniqueId;
    private String mobile;
    private String name;
    private String password;
    private String birth;
    private int sex;
    private String avatar;
    private String motto;
    private String wechat;
    private String createTime;
    private String updateTime;
    private String deadlineTime;
    private int status;

    public Customer() {
    }

    public Customer(String id, String uniqueId, String mobile, String name, String password, String birth, int sex, String avatar, String motto, String wechat, String createTime, String updateTime, String deadlineTime, int status) {
        this.id = id;
        this.uniqueId = uniqueId;
        this.mobile = mobile;
        this.name = name;
        this.password = password;
        this.birth = birth;
        this.sex = sex;
        this.avatar = avatar;
        this.motto = motto;
        this.wechat = wechat;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.deadlineTime = deadlineTime;
        this.status = status;
    }
}
