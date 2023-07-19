package net.cctv3.BaijiaJiangtan;

import com.alibaba.fastjson.JSON;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeChatEmojisParser {
    private static long splitCdnSrc(String src) {
        long start = System.currentTimeMillis();
        try {
            URL url = new URL(src);
            BufferedImage parent = ImageIO.read(url.openStream());
            // BufferedImage child = parent.getSubimage(0, 0, 42, 42);
            // BufferedImage child = parent.getSubimage(64, 0, 42, 42);
            for (int i = 0; i < 14; i++) {
                for (int j = 0; j < 8; j++) {
                    int y = 65 * i + (int) i / 2;
                    int x = j == 7 ? parent.getWidth() - 42 : 65 * j;
                    BufferedImage child = parent.getSubimage(x, y, 42, 42);
                    File file = new File(i + "-" + j + ".png");
                    if(!file.exists()) {
                        file.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(file);
                    ImageIO.write(child, "png", fos);
                    fos.flush();
                    fos.close();
                    System.out.println("File: " + file.getAbsolutePath());
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        return end - start;
    }

    private static String files2JSONString(File[] files) {
        HashMap<String, Object> root = new HashMap<>();
        List<HashMap<String, String>> list = new ArrayList<>();
        for(File file : files) {
            // System.out.println(file.getName());
            if(file.getName().endsWith(".png")) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("icon", file.getName());
                hashMap.put("text", file.getName().replace(".png", ""));
                list.add(hashMap);
            }
        }
        root.put("container", list);
        root.put("type", "image");
        HashMap<String, Object> result = new HashMap<>();
        result.put("微信", root);
        return JSON.toJSONString(result);
    }

    public static void main(String[] args) {
        // long time = splitCdnSrc("https://cdn.cctv3.net/net.cctv3.typecho/wechat-emoji.png");
        String json = files2JSONString(new File("/Users/net.cctv3.i/net.cctv3.BaijiaJiangtan/service").listFiles());
        System.out.println(json);
    }
}