package net.cctv3.BaijiaJiangtan.imp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cctv3.BaijiaJiangtan.bean.Series;
import net.cctv3.BaijiaJiangtan.bean.Teacher;
import net.cctv3.BaijiaJiangtan.dao.SeriesDAO;
import net.cctv3.BaijiaJiangtan.dao.TeacherDAO;
import net.cctv3.BaijiaJiangtan.service.SeriesService;
import net.cctv3.BaijiaJiangtan.service.TeacherService;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

@Service
public class TeacherServiceImp implements TeacherService {
    @Autowired
    TeacherDAO teacherDAO;

    @Override
    public boolean saveBatch(Collection<Teacher> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Teacher> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Teacher> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Teacher entity) {
        int rows = 0;
        Teacher cacheTeacher = teacherDAO.selectById(entity.getId());
        if(cacheTeacher == null) {
            entity.setId(StringUtils.useUUID());
            rows = teacherDAO.insert(entity);
        }
        else {
            rows = teacherDAO.updateById(entity);
        }
        return rows == 1;
    }

    @Override
    public Teacher getOne(Wrapper<Teacher> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Teacher> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Teacher> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Teacher> getBaseMapper() {
        return null;
    }

    @Override
    public Class<Teacher> getEntityClass() {
        return null;
    }

    @Override
    public boolean removeById(Serializable id) {
        int rows = teacherDAO.deleteById(id);
        return rows > 0;
    }
}