package net.cctv3.BaijiaJiangtan.action;

import net.cctv3.BaijiaJiangtan.bean.Chapter;
import net.cctv3.BaijiaJiangtan.bean.Teacher;
import net.cctv3.BaijiaJiangtan.dao.ChapterDAO;
import net.cctv3.BaijiaJiangtan.dao.TeacherDAO;
import net.cctv3.BaijiaJiangtan.service.ChapterService;
import net.cctv3.BaijiaJiangtan.x.BaijiaJiangtanUtils;
import net.cctv3.BaijiaJiangtan.x.HttpClientUtils;
import net.cctv3.BaijiaJiangtan.x.LogUtils;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class ChapterAction {
    LogUtils logUtils = new LogUtils(ChapterAction.class);
    @Autowired
    ChapterDAO chapterDAO;
    @Autowired
    ChapterService chapterService;

    @CrossOrigin
    @PostMapping("/mergeChapter.do")
    public String mergeChapter(@RequestBody Chapter chapter) {
        boolean success = chapterService.saveOrUpdate(chapter);
        logUtils.log("mergeChapter: {}", new Object[]{chapter});
        return StringUtils.response(success);
    }

    @CrossOrigin
    @GetMapping("/selectChaptersByStatus.do")
    public String selectChaptersByStatus(@RequestParam(value = "status") int status) {
        List<Chapter> chapters = chapterDAO.selectChaptersByStatus(status);
        return StringUtils.response(true, chapters, chapters.size());
    }

    @CrossOrigin
    @GetMapping("/deleteChapter.do")
    public String deleteSeries(@RequestParam(value = "id") String id) {
        int rows = chapterDAO.deleteById(id);
        return StringUtils.response(rows > 0);
    }

    @CrossOrigin
    @GetMapping("/selectChaptersBySeries.do")
    public String selectChaptersBySeries(@RequestParam(value = "id") String id) {
        List<Chapter> chapters = chapterDAO.selectChaptersBySeries(id);
        return StringUtils.response(true, chapters, chapters.size());
    }

    @CrossOrigin
    @GetMapping("/selectChapter.do")
    public String selectChapter(@RequestParam(value = "id") String id) {
        Chapter chapter = chapterDAO.selectChapter(id);
        return StringUtils.response(true, chapter);
    }

    /**
     * 文件存储在 OSS 服务器，`${seriesId}.html
     *
     * @param id
     * @return
     */
    @CrossOrigin
    @GetMapping("/spiderChaptersBySeriesId.do")
    public String spiderChaptersBySeriesId(@RequestParam(value = "id") String id, @RequestParam(value = "endWith", required = false) String endWith) {
        int rows = 0;
        String html = HttpClientUtils.doGet(String.format("https://net-cctv3.oss-cn-qingdao.aliyuncs.com/net.cctv3.BaijiaJiangtan/%s.html", id), "utf-8");
        if (html.equals("")) {
            // 文件不存在
        } else {
            Document document = Jsoup.parse(html);
            Elements boxes = document.getElementsByClass("box");
            for (Element box : boxes) {
                if (box.html().toString().contains("javascript")) {
                    // 跳过
                } else {
                    logUtils.log("box: {}", new Object[]{box.html()});
                    Element p = box.getElementsByTag("p").first();
                    String title = p.text().replace("\\s", "");
                    if(endWith == null || endWith.equals("")) {

                    }
                    else {
                        title += endWith;
                    }
                    Element a = p.getElementsByTag("a").first();
                    String href = a.attr("href");
                    HashMap hashMap = BaijiaJiangtanUtils.cctvDetailHTMLParser(href);
                    String cctvUUID = (String) hashMap.get("uuid");
                    String description = (String) hashMap.get("description").toString();
                    Chapter chapter = chapterDAO.selectChapterByCCTVID(cctvUUID);
                    if (chapter == null) {
                        HashMap more = BaijiaJiangtanUtils.cctvGetHttpVideoInfoDo(cctvUUID);
                        if (more.isEmpty()) {

                        } else {
                            String m3u8 = (String) more.get("m3u8");
                            String intervals = (String) more.get("intervals");
                            String urls = (String) more.get("urls");
                            String mp3 = (String) more.get("mp3");
                            int duration = (int) more.get("duration");
                            String time = (String) more.get("time");
                            String capture = (String) more.get("capture");
                            Chapter myChapter = new Chapter(StringUtils.useUUID(), "", cctvUUID, title, capture, description, m3u8, href, intervals, urls, mp3, duration, time, StringUtils.useTimeFormatter(new Date()), StringUtils.useTimeFormatter(new Date()), 0);
                            logUtils.log("cctv: {} is not in MySQL.", new Object[]{cctvUUID});
                            logUtils.log("chapter: {}", new Object[]{myChapter});
                            rows += chapterService.saveOrUpdate(myChapter) ? 1 : 0;
                        }
                    } else {
                        logUtils.log("cctv: {} is already in MySQL.", new Object[]{cctvUUID});
                        // 防止已经爬虫的名字起的不对，正则表达式找不到
                        rows += chapterDAO.updateChapterTitleByCCTVID(cctvUUID, title);
                    }
                }
                logUtils.log("------>", new Object[]{});
            }
        }
        logUtils.log("{}.html: {}", new Object[]{id, html.length()});
        return StringUtils.response(rows > 0);
    }

    /**
     * @param href
     * @return
     */
    @CrossOrigin
    @PostMapping("/spiderChaptersByCCTVDetailHref.do")
    public String spiderChaptersByHref(
            @RequestParam(value = "href") String href,
            @RequestParam(value = "seriesId") String seriesId,
            @RequestParam(value = "title") String title
    ) {
        logUtils.log("------>", new Object[]{});
        HashMap hashMap = BaijiaJiangtanUtils.cctvDetailHTMLParser(href);
        String cctvUUID = (String) hashMap.get("uuid");
        String description = (String) hashMap.get("description");
        Chapter chapter = chapterDAO.selectChapterByCCTVID(cctvUUID);
        Chapter myChapter = null;
        boolean success = false;
        if (chapter == null) {
            HashMap more = BaijiaJiangtanUtils.cctvGetHttpVideoInfoDo(cctvUUID);
            if (more.isEmpty()) {

            } else {
                String m3u8 = (String) more.get("m3u8");
                String intervals = (String) more.get("intervals");
                String urls = (String) more.get("urls");
                String mp3 = (String) more.get("mp3");
                int duration = (int) more.get("duration");
                String time = (String) more.get("time");
                String capture = (String) more.get("capture");
                myChapter = new Chapter(StringUtils.useUUID(), seriesId, cctvUUID, title, capture, description, m3u8, href, intervals, urls, mp3, duration, time, StringUtils.useTimeFormatter(new Date()), StringUtils.useTimeFormatter(new Date()), 0);
                success = chapterService.saveOrUpdate(myChapter);
                logUtils.log("cctv: {} is not in MySQL.", new Object[]{cctvUUID});
                logUtils.log("chapter: {}", new Object[]{myChapter});
            }
        }
        return StringUtils.response(success, chapter);
    }
}