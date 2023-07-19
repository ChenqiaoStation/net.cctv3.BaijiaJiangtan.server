package net.cctv3.BaijiaJiangtan.imp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cctv3.BaijiaJiangtan.bean.Statistics;
import net.cctv3.BaijiaJiangtan.dao.StatisticsDAO;
import net.cctv3.BaijiaJiangtan.service.StatisticsService;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class StatisticsServiceImp implements StatisticsService {
    @Autowired
    StatisticsDAO statisticsDAO;

    @Override
    public boolean saveBatch(Collection<Statistics> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Statistics> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Statistics> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Statistics entity) {
        int rows = 0;
        if (entity.getId() == null || entity.getId().equals("")) {
            entity.setId(StringUtils.useUUID());
        }
        Statistics statistics = statisticsDAO.selectById(entity.getId());
        entity.setTime(StringUtils.useTimeFormatter(new Date()));
        if (statistics == null) {
            rows = statisticsDAO.insert(entity);
        } else {
            rows = statisticsDAO.updateById(entity);
        }
        return rows > 0;
    }

    @Override
    public int deleteStatisticses(String customerId, String batteredId, String intent) {
        QueryWrapper<Statistics> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_id", customerId).eq("battered_id", batteredId).eq("intent", intent);
        int rows = statisticsDAO.delete(wrapper);
        return rows;
    }

    @Override
    public Statistics getOne(Wrapper<Statistics> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Statistics> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Statistics> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Statistics> getBaseMapper() {
        return null;
    }

    @Override
    public Class<Statistics> getEntityClass() {
        return null;
    }

    @Override
    public boolean removeById(Serializable id) {
        int rows = statisticsDAO.deleteById(id);
        return rows > 0;
    }

    @Override
    public boolean selectIsIntentInStatistics(String customerId, String batteredId, String intent) {
        QueryWrapper<Statistics> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_id", customerId).eq("battered_id", batteredId).eq("intent", intent);
        boolean success = statisticsDAO.selectCount(wrapper) > 0;
        return success;
    }


}