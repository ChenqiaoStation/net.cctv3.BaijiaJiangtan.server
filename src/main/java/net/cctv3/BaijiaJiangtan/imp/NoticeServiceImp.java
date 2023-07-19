package net.cctv3.BaijiaJiangtan.imp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cctv3.BaijiaJiangtan.bean.Notice;
import net.cctv3.BaijiaJiangtan.bean.Notice;
import net.cctv3.BaijiaJiangtan.dao.NoticeDAO;
import net.cctv3.BaijiaJiangtan.dao.NoticeDAO;
import net.cctv3.BaijiaJiangtan.service.NoticeService;
import net.cctv3.BaijiaJiangtan.service.NoticeService;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class NoticeServiceImp implements NoticeService {
    @Autowired
    NoticeDAO noticeDAO;

    @Override
    public boolean saveBatch(Collection<Notice> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Notice> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Notice> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Notice entity) {
        if (entity.getId() == null || entity.getId().equals("")) {
            entity.setId(StringUtils.useUUID());
        }
        Notice cacheNotice = noticeDAO.selectById(entity.getId());
        int rows = 0;
        if (cacheNotice == null) {
            rows = noticeDAO.insert(entity);
        } else {
            rows = noticeDAO.updateById(entity);
        }
        return rows == 1;
    }

    @Override
    public Notice getOne(Wrapper<Notice> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Notice> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Notice> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Notice> getBaseMapper() {
        return null;
    }

    @Override
    public Class<Notice> getEntityClass() {
        return null;
    }

    @Override
    public boolean removeById(Serializable id) {
        Notice notice = noticeDAO.selectById(id);
        boolean success = false;
        if (notice == null) {
        } else {
            notice.setStatus(0);
            success = saveOrUpdate(notice);
        }
        return success;
    }

    @Override
    public List<Notice> selectNotices(Integer status, Integer limits) {
        QueryWrapper wrapper = new QueryWrapper();
        if (status == null) {
        } else {
            wrapper.eq("status", status);
        }
        if (limits == null) {
        } else {
            wrapper.last(String.format("limit %d", limits));
        }
        List<Notice> list = noticeDAO.selectList(wrapper);
        return list;
    }
}