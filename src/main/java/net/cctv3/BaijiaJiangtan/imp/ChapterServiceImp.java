package net.cctv3.BaijiaJiangtan.imp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cctv3.BaijiaJiangtan.bean.Chapter;
import net.cctv3.BaijiaJiangtan.dao.ChapterDAO;
import net.cctv3.BaijiaJiangtan.service.ChapterService;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

@Service
public class ChapterServiceImp implements ChapterService {
    @Autowired
    ChapterDAO chapterDAO;

    @Override
    public boolean saveBatch(Collection<Chapter> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Chapter> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Chapter> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Chapter entity) {
        int rows = 0;
        if(entity.getId() == null) {
            entity.setId(StringUtils.useUUID());
        }
        Chapter cacheChapter = chapterDAO.selectById(entity.getId());
        if(cacheChapter == null) {
            rows = chapterDAO.insert(entity);
        }
        else {
            rows = chapterDAO.updateById(entity);
        }
        return rows == 1;
    }

    @Override
    public Chapter getOne(Wrapper<Chapter> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Chapter> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Chapter> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Chapter> getBaseMapper() {
        return null;
    }

    @Override
    public Class<Chapter> getEntityClass() {
        return null;
    }
}
