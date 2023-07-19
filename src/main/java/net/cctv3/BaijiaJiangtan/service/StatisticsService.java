package net.cctv3.BaijiaJiangtan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.cctv3.BaijiaJiangtan.bean.Chapter;
import net.cctv3.BaijiaJiangtan.bean.Statistics;
import org.springframework.stereotype.Service;

public interface StatisticsService extends IService<Statistics> {
    boolean selectIsIntentInStatistics(String customerId, String batteredId, String intent);
    int deleteStatisticses(String customerId, String batteredId, String intent);
}