package net.cctv3.BaijiaJiangtan.action;

import net.cctv3.BaijiaJiangtan.bean.Notice;
import net.cctv3.BaijiaJiangtan.bean.SelectSeriesByYear;
import net.cctv3.BaijiaJiangtan.bean.Series;
import net.cctv3.BaijiaJiangtan.dao.NoticeDAO;
import net.cctv3.BaijiaJiangtan.dao.SeriesDAO;
import net.cctv3.BaijiaJiangtan.service.NoticeService;
import net.cctv3.BaijiaJiangtan.service.SeriesService;
import net.cctv3.BaijiaJiangtan.x.LogUtils;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class NoticeAction {
    LogUtils logUtils = new LogUtils(NoticeAction.class);
    @Autowired
    NoticeDAO noticeDAO;
    @Autowired
    NoticeService noticeService;

    @CrossOrigin
    @PostMapping("/mergeNotice.do")
    public String mergeNotice(@RequestBody Notice notice) {
        boolean success = noticeService.saveOrUpdate(notice);
        logUtils.log("mergeNotice: {}", new Object[]{notice});
        return StringUtils.response(success);
    }

    @CrossOrigin
    @GetMapping("/selectNotices.do")
    public String selectNotices(
            @RequestParam(value = "limits", required = false) Integer limits,
            @RequestParam(value = "status", required = false) Integer status
    ) {
        List<Notice> series = noticeService.selectNotices(status, limits);
        return StringUtils.response(true, series, series.size());
    }

    @CrossOrigin
    @GetMapping("/deleteNotice.do")
    public String deleteTeacher(@RequestParam(value = "id") String id) {
        boolean success = noticeService.removeById(id);
        return StringUtils.response(success);
    }
}