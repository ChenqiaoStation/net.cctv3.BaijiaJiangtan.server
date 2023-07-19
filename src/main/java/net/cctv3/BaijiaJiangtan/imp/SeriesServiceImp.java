package net.cctv3.BaijiaJiangtan.imp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cctv3.BaijiaJiangtan.bean.Series;
import net.cctv3.BaijiaJiangtan.dao.SeriesDAO;
import net.cctv3.BaijiaJiangtan.service.SeriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

@Service
public class SeriesServiceImp implements SeriesService {
    @Autowired
    SeriesDAO seriesDAO;

    @Override
    public boolean saveBatch(Collection<Series> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Series> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Series> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Series entity) {
        Series cacheSeries = seriesDAO.selectById(entity.getId());
        int rows = 0;
        if(cacheSeries == null) {
            rows = seriesDAO.insert(entity);
        }
        else {
            rows = seriesDAO.updateById(entity);
        }
        return rows == 1;
    }

    @Override
    public Series getOne(Wrapper<Series> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Series> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Series> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Series> getBaseMapper() {
        return null;
    }

    @Override
    public Class<Series> getEntityClass() {
        return null;
    }

    @Override
    public boolean removeById(Serializable id) {
        int rows = seriesDAO.deleteById(id);
        return rows > 0;
    }
}