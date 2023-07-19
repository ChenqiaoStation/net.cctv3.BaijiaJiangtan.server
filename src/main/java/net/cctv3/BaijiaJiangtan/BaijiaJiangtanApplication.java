package net.cctv3.BaijiaJiangtan;

import net.cctv3.BaijiaJiangtan.x.BaijiaJiangtanUtils;
import net.cctv3.BaijiaJiangtan.x.HttpClientUtils;
import net.cctv3.BaijiaJiangtan.x.LogUtils;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.apache.log4j.PropertyConfigurator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class BaijiaJiangtanApplication {
    static LogUtils logUtils = new LogUtils(BaijiaJiangtanApplication.class);
    public static void main(String[] args) {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        SpringApplication.run(BaijiaJiangtanApplication.class, args);
        // HttpClientUtils.doGet("https://tv.cctv.com/2010/03/30/VIDE1355519228887503.shtml?spm=C59377.PhY3mfGXKdo3.EmOwVbjiQP0D.3318", "utf-8");
    }
}