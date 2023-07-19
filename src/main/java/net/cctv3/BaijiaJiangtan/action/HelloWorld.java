package net.cctv3.BaijiaJiangtan.action;

import com.alibaba.fastjson.JSON;
import net.cctv3.BaijiaJiangtan.bean.Chapter;
import net.cctv3.BaijiaJiangtan.dao.ChapterDAO;
import net.cctv3.BaijiaJiangtan.service.ChapterService;
import net.cctv3.BaijiaJiangtan.x.BaijiaJiangtanUtils;
import net.cctv3.BaijiaJiangtan.x.HttpClientUtils;
import net.cctv3.BaijiaJiangtan.x.LogUtils;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
public class HelloWorld {
    LogUtils logUtils = new LogUtils(HelloWorld.class);
    @Autowired
    ChapterDAO chapterDAO;
    @Autowired
    ChapterService chapterService;

    @CrossOrigin
    @GetMapping(value = "/baijiaJiangtanSpider.do")
    public String baijiaJiangtanSpider(
            @RequestParam(value = "year", required = true) int year,
            @RequestParam(value = "mysql", required = true) int mysql
    ) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long allTimerStart = System.currentTimeMillis();
        Document document = BaijiaJiangtanUtils.readHTML(String.format("%d.html", year));
        Element element = document.getElementById("boxList");
        Elements elements = element.getElementsByClass("e");
        /** Document's size */
        hashMap.put("status", 1);
        hashMap.put("data", elements.size());
        logUtils.log("Document Chaters.size = {}", new Object[]{elements.size()});
        int index = 0;
        for (Element e : elements) {
            Elements images = e.getElementsByClass("image");
            Element image = images.first();
            long timeStart = System.currentTimeMillis();
            Element img = image.getElementsByAttribute("title").first();
            String cover = img.attr("data-echo");
            Element a = image.getElementsByTag("a").first();
            String href = a.attr("href");
            // System.out.println("Guid: " + findVideoGuidByUrl(href));
            // String uuid = findVideoGuidByUrl(href);
            /** 爬虫详情页 */
            HashMap<String, String> cctvDetailSpiderParams = BaijiaJiangtanUtils.cctvDetailHTMLParser(href);
            String uuid = cctvDetailSpiderParams.get("uuid");
            if(uuid.equals("")) {
                /** 官网已经报错 404 了 */
            }
            else {
                String message = cctvDetailSpiderParams.get("description");
                // StringUtils.log(BaijiaJiangtanUtils.class, "cctvDetailSpiderParams['description']: {}", new Object[]{message});
                /** 请求 CCTV 自带接口 */
                String api = String.format("https://vdn.apps.cntv.cn/api/getHttpVideoInfo.do?pid=%s", uuid);
                logUtils.log("GetHttpVideoInfo.do: {}", new Object[]{api});
                String httpVideoInfo = HttpClientUtils.doGet(api, "utf-8");
                // StringUtils.log(BaijiaJiangtanUtils.class, "HttpClientUtils.doGet: {}", new Object[]{httpVideoInfo});
                JSONObject root = new JSONObject(httpVideoInfo);
                if(root.getString("ack").equals("yes")) {
                    index++;
                    String time = root.getString("f_pgmtime");
                    String m3u8 = root.getString("hls_url");
                    String mp3 = root.getJSONObject("manifest").getString("hls_audio_url");
                    String title = root.getString("title");
                    int duration = (int) root.getJSONObject("video").getDouble("totalLength");
                    JSONArray chapters = root.getJSONObject("video").getJSONArray("chapters");

                    /** CCTV 视频分段存储 CDN */
                    String intervals = BaijiaJiangtanUtils.chaptersJSONArray2String(chapters, "duration");
                    String urls = BaijiaJiangtanUtils.chaptersJSONArray2String(chapters, "url");;

                    /** myChapter */
                    Chapter myChapter = new Chapter(StringUtils.useUUID(), "", uuid, title, cover, message, m3u8, href, intervals, urls, mp3, duration, time, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), 1);
                    // StringUtils.log(BaijiaJiangtanUtils.class, "findDescriptionInMetas: {}", new Object[]{message});
                    logUtils.log("Spider 第{}组: {}", new Object[]{index, myChapter});
                    if(mysql == 1 && chapterDAO.selectChapterByCCTVID(uuid) == null) {
                       chapterService.saveOrUpdate(myChapter);
                    }
                    long timeEnd = System.currentTimeMillis();
                    // logUtils.log("Spider time: {}ms", new Object[]{timeEnd - timeStart});
                } else {
                    /**
                     * 有的虽然网址能打开，但是视频不存在，接口报错
                     * https://tv.cctv.com/2011/01/12/VIDELBCIouJTGXjCIeKWzUSq110112.shtml
                     * https://vdn.apps.cntv.cn/api/getHttpVideoInfo.do?pid=71a70e06ac1032c800c989007a539581
                     * */
                }
            }
        }
        long allTimerEnd = System.currentTimeMillis();
        hashMap.put("time", (allTimerEnd - allTimerStart)  + "ms");
        return JSON.toJSONString(hashMap);
    }
}