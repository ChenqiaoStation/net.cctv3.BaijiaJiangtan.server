package net.cctv3.BaijiaJiangtan.action;

import net.cctv3.BaijiaJiangtan.bean.Series;
import net.cctv3.BaijiaJiangtan.bean.Statistics;
import net.cctv3.BaijiaJiangtan.dao.SeriesDAO;
import net.cctv3.BaijiaJiangtan.dao.StatisticsDAO;
import net.cctv3.BaijiaJiangtan.service.StatisticsService;
import net.cctv3.BaijiaJiangtan.x.LogUtils;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StatisticsAction {
    LogUtils logUtils = new LogUtils(StatisticsAction.class);
    @Autowired
    StatisticsService statisticsService;
    @CrossOrigin
    @PostMapping ("/mergeStatistics.do")
    public String mergeStatistics(@RequestBody Statistics statistics) {
        boolean success = statisticsService.saveOrUpdate(statistics);
        logUtils.log("mergeStatistics: {}", new Object[]{statistics});
        return StringUtils.response(success);
    }

    @CrossOrigin
    @GetMapping ("/deleteStatistics.do")
    public String deleteStatistics(@RequestParam(value = "id") String id) {
        boolean success = statisticsService.removeById(id);
        logUtils.log("deleteStatistics: {}", new Object[]{id});
        return StringUtils.response(success);
    }

    @CrossOrigin
    @GetMapping("selectIsIntentInStatistics.do")
    public String selectIsIntentInStatistics(
            @RequestParam(value = "customerId", required = true) String customerId,
            @RequestParam(value = "batteredId", required = true) String batteredId,
            @RequestParam(value = "intent", required = true) String intent
    ) {
        boolean success = statisticsService.selectIsIntentInStatistics(customerId, batteredId, intent);
        return StringUtils.response(success);
    }

    @CrossOrigin
    @GetMapping ("/deleteStatisticses.do")
    public String deleteStatisticses(
            @RequestParam(value = "customerId", required = true) String customerId,
            @RequestParam(value = "batteredId", required = true) String batteredId,
            @RequestParam(value = "intent", required = true) String intent
    ) {
        int rows = statisticsService.deleteStatisticses(customerId, batteredId, intent);
        logUtils.log("deleteStatisticses: {}", new Object[]{rows});
        return StringUtils.response(rows > 0);
    }
}