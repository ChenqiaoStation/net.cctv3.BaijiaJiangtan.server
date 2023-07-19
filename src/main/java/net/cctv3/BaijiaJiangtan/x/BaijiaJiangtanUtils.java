package net.cctv3.BaijiaJiangtan.x;

import net.cctv3.BaijiaJiangtan.bean.Chapter;
import net.cctv3.BaijiaJiangtan.dao.ChapterDAO;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaijiaJiangtanUtils {
    private static LogUtils logUtils = new LogUtils(BaijiaJiangtanUtils.class);

    /**
     * 解析本地 HTML
     *
     * @param file
     * @return
     */
    public static Document readHTML(String file) {
        Document document = null;
        try {
            document = Jsoup.parse(
                    /** 源代码的 class=""，手动加一个 class */
                    FileUtils.readFileToString(
                            new File(String.format("/Users/net.cctv3.i/net.cctv3.BaijiaJiangtan/caches/%s", file))
                    ).replace("class=\"\"", "class=\"e\"")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 详情页抓取多个信息到 HashMap
     *
     * @param url
     * @return
     */
    public static HashMap<String, String> cctvDetailHTMLParser(String url) {
        String uuid = "";
        String description = "";
        HashMap<String, String> hashMap = new HashMap<>();
        String html = HttpClientUtils.doGet(url, "UTF-8");
        logUtils.log("CCTVDetailHTMLParser: {}", new Object[]{url});
        if(html.equals("")) {
            /** 详情页官网已经报错 404 了 */
        }
        else {
            String strings[] = html.split("\n");
            /** 解析 <meta /> 取 description 属性 */
            Document document = Jsoup.parse(html);
            Elements metas = document.getElementsByTag("meta");
            description = findDescriptionInMetas(metas);

            /** 正则表达式取 Guid */
            for (String s : strings) {
                if (s.trim().startsWith("var guid =")) {
                    /**
                     * 并非所有的 Guid 都是 32位
                     * https://tv.cctv.com/v/v1/VIDE1420017139701791.html → guid = 90104
                     * */
                    Pattern pattern = Pattern.compile("\"([a-fA-F0-9]{1,})\"");
                    Matcher matcher = pattern.matcher(s.trim());
                    if (matcher.find()) {
                        uuid = matcher.group(1);
                    }
                }
            }
            // System.out.println("findVideoGuidByUrl strings: " + strings.length);
            // System.out.println(html.matches("var guid = \"[0-9][a-z]{32}\""));
        }
        hashMap.put("uuid", uuid);
        hashMap.put("description", description);
        return hashMap;
    }

    /**
     * 央视原始的链接
     * @param id
     * @return
     */
    public static HashMap<String, Object> cctvGetHttpVideoInfoDo(String id) {
        HashMap hashMap = new HashMap();
        /** 请求 CCTV 自带接口 */
        String api = String.format("https://vdn.apps.cntv.cn/api/getHttpVideoInfo.do?pid=%s", id);
        logUtils.log("GetHttpVideoInfo.do: {}", new Object[]{api});
        String httpVideoInfo = HttpClientUtils.doGet(api, "utf-8");
        // StringUtils.log(BaijiaJiangtanUtils.class, "HttpClientUtils.doGet: {}", new Object[]{httpVideoInfo});
        JSONObject root = new JSONObject(httpVideoInfo);
        if(root.getString("ack").equals("yes")) {
            String time = root.getString("f_pgmtime");
            String m3u8 = root.getString("hls_url");
            String mp3 = root.getJSONObject("manifest").getString("hls_audio_url");
            String title = root.getString("title").replace("\\s", "");
            int duration = (int) root.getJSONObject("video").getDouble("totalLength");
            JSONArray chapters = root.getJSONObject("video").getJSONArray("chapters");
            String capture = "";
            if(chapters.length() > 0) {
                capture = chapters.getJSONObject(0).getString("image");
            }
            /** CCTV 视频分段存储 CDN */
            String intervals = BaijiaJiangtanUtils.chaptersJSONArray2String(chapters, "duration");
            String urls = BaijiaJiangtanUtils.chaptersJSONArray2String(chapters, "url");;
            // logUtils.log("Spider time: {}ms", new Object[]{timeEnd - timeStart});
            String keys[] = new String[]{"time", "m3u8", "mp3", "title", "duration", "intervals", "urls", "capture"};
            Object values[] = new Object[]{time, m3u8, mp3, title, duration, intervals, urls, capture};
            for(int i=0;i<keys.length;i++) {
                hashMap.put(keys[i], values[i]);
            }
        } else {
            /**
             * 有的虽然网址能打开，但是视频不存在，接口报错
             * https://tv.cctv.com/2011/01/12/VIDELBCIouJTGXjCIeKWzUSq110112.shtml
             * https://vdn.apps.cntv.cn/api/getHttpVideoInfo.do?pid=71a70e06ac1032c800c989007a539581
             * */
        }
        return hashMap;
    }

    public static String findDescriptionInMetas(Elements metas) {
        String result = "";
        for (Element meta : metas) {
            if (meta.attr("property").equals("og:description")) {
                result = meta.attr("content");
            }
        }
        return result.replaceAll("\\p{C}", "");
    }

    /**
     * 有的视频没有切片 https://vdn.apps.cntv.cn/api/getHttpVideoInfo.do?pid=90104 → chapters.length() = 1
     * @param chapters
     * @param key
     * @return
     */
    public static String chaptersJSONArray2String(JSONArray chapters, String key) {
        String result = "";
        if (chapters.length() == 1) {
            return chapters.getJSONObject(0).getString(key);
        } else {
            for (int i = 0; i < chapters.length() - 1; i++) {
                JSONObject chapter = chapters.getJSONObject(i);
                result += chapter.getString(key) + "##";
            }
            JSONObject chapter = chapters.getJSONObject(chapters.length() - 1);
            result += chapter.getString(key);
        }
        return result;
    }
}