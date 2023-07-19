package net.cctv3.BaijiaJiangtan.imp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cctv3.BaijiaJiangtan.bean.Search;
import net.cctv3.BaijiaJiangtan.bean.SimpleMap;
import net.cctv3.BaijiaJiangtan.dao.SearchDAO;
import net.cctv3.BaijiaJiangtan.service.SearchService;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class SearchServiceImp implements SearchService {
    @Autowired
    SearchDAO searchDAO;

    @Override
    public boolean saveBatch(Collection<Search> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Search> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Search> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Search entity) {
        entity.setTime(StringUtils.useTimeFormatter(new Date()));
        Search cacheSearch = searchDAO.selectById(entity.getId());
        int rows = 0;
        if (cacheSearch == null) {
            entity.setId(StringUtils.useUUID());
            rows = searchDAO.insert(entity);
        } else {
            rows = searchDAO.updateById(entity);
        }
        return rows == 1;
    }

    @Override
    public Search getOne(Wrapper<Search> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Search> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Search> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Search> getBaseMapper() {
        return null;
    }

    @Override
    public Class<Search> getEntityClass() {
        return null;
    }

    @Override
    public boolean removeById(Serializable id) {
        boolean succuss = false;
        Search cacheSearch = searchDAO.selectById(id);
        if (cacheSearch == null) {

        } else {
            cacheSearch.setStatus(0);
        }
        succuss = saveOrUpdate(cacheSearch);
        return succuss;
    }

    @Override
    public List<SimpleMap> selectSearchesByCounts(Integer limits) {
        return searchDAO.selectSearchesByCounts(limits);
    }

    @Override
    public List<Search> selectSearchesByCustomer(String id, Integer limits, Integer status) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.select("DISTINCT keywords, id, status, time");
        wrapper.eq("customer_id", id);
        if (status == null) {

        } else {
            wrapper.eq("status", status);
        }
        if (limits == null) {

        } else {
            wrapper.last(String.format("limit %d", limits));
        }
        wrapper.orderByDesc("time");
        List<Search> list = searchDAO.selectList(wrapper);
        return list;
    }
}