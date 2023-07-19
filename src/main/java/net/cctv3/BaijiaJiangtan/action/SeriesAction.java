package net.cctv3.BaijiaJiangtan.action;

import com.alibaba.fastjson.JSON;
import net.cctv3.BaijiaJiangtan.bean.Proof;
import net.cctv3.BaijiaJiangtan.bean.SelectSeriesByYear;
import net.cctv3.BaijiaJiangtan.bean.Series;
import net.cctv3.BaijiaJiangtan.dao.ProofDAO;
import net.cctv3.BaijiaJiangtan.dao.SeriesDAO;
import net.cctv3.BaijiaJiangtan.service.SeriesService;
import net.cctv3.BaijiaJiangtan.x.LogUtils;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class SeriesAction {
    LogUtils logUtils = new LogUtils(SeriesAction.class);
    @Autowired
    SeriesDAO seriesDAO;
    @Autowired
    SeriesService seriesService;
    @CrossOrigin
    @PostMapping ("/mergeSeries.do")
    public String mergeSeries(@RequestBody Series series) {
        if(series.getId() == null || series.getId().equals("")) {
            // 新增
            series.setId(StringUtils.useUUID());
        }
        boolean success = seriesService.saveOrUpdate(series);
        logUtils.log("mergeSeries: {}", new Object[]{series});
        return StringUtils.response(success);
    }

    @CrossOrigin
    @GetMapping ("/selectSerieses.do")
    public String selectSerieses() {
        List<Series> series = seriesDAO.findSerieses();
        return StringUtils.response(true, series, series.size());
    }

    @CrossOrigin
    @GetMapping ("/deleteSeries.do")
    public String deleteTeacher(@RequestParam(value = "id") String id) {
        boolean success = seriesService.removeById(id);
        return StringUtils.response(success);
    }

    @CrossOrigin
    @GetMapping ("/selectSeriesesByYear.do")
    public String selectSeriesesByYear(@RequestParam(value = "year") int year) {
        List<SelectSeriesByYear> series = seriesDAO.selectSeriesesByYear(year);
        return StringUtils.response(true, series, series.size());
    }

    @CrossOrigin
    @GetMapping ("/selectLatest10UpdatedSerieses.do")
    public String selectLatest10UpdatedSerieses() {
        List<Series> series = seriesDAO.selectLatest10UpdatedSerieses();
        return StringUtils.response(true, series, series.size());
    }

    @CrossOrigin
    @GetMapping ("/selectShuffleSerieses.do")
    public String selectShuffleSerieses(@RequestParam(value = "total") int total) {
        List<Series> series = seriesDAO.selectShuffleSerieses(total);
        return StringUtils.response(true, series, series.size());
    }

    @CrossOrigin
    @GetMapping ("/selectSeries.do")
    public String selectSeries(@RequestParam(value = "id") String id) {
        Series series = seriesDAO.selectById(id);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("data", series);
        return StringUtils.response(true, hashMap);
    }
}