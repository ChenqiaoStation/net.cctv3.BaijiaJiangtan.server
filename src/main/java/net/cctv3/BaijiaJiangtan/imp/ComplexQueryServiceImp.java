package net.cctv3.BaijiaJiangtan.imp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cctv3.BaijiaJiangtan.bean.*;
import net.cctv3.BaijiaJiangtan.dao.ComplexQueryDAO;
import net.cctv3.BaijiaJiangtan.service.ComplexQueryService;
import net.cctv3.BaijiaJiangtan.x.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class ComplexQueryServiceImp implements ComplexQueryService {
    @Autowired
    ComplexQueryDAO complexQueryDAO;
    @Override
    public List<SelectSearchChapter> selectSearchChapters(String keywords, int pageIndex, int pageSize) {
        PageUtils pageUtils = new PageUtils(pageIndex, pageSize);
        List<SelectSearchChapter> list = complexQueryDAO.selectSearchChapters(
                keywords,
                pageUtils.getPageStart(),
                pageUtils.getPageEnd()
        );
        return list;
    }

    @Override
    public List<SelectSearchTeacher> selectSearchTeachers(String keywords, int pageIndex, int pageSize) {
        PageUtils pageUtils = new PageUtils(pageIndex, pageSize);
        List<SelectSearchTeacher> list = complexQueryDAO.selectSearchTeachers(
                keywords,
                pageUtils.getPageStart(),
                pageUtils.getPageEnd()
        );
        return list;
    }

    @Override
    public List<SelectTeacherAndCount> selectTeachersAndCounts(int pageIndex, int pageSize) {
        PageUtils pageUtils = new PageUtils(pageIndex, pageSize);
        List<SelectTeacherAndCount> list = complexQueryDAO.selectTeachersAndCounts(
                pageUtils.getPageStart(),
                pageUtils.getPageEnd()
        );
        return list;
    }

    @Override
    public List<SelectSearchSeries> selectSearchSerieses(String keywords, int pageIndex, int pageSize) {
        PageUtils pageUtils = new PageUtils(pageIndex, pageSize);
        List<SelectSearchSeries> list = complexQueryDAO.selectSearchSerieses(
                keywords,
                pageUtils.getPageStart(),
                pageUtils.getPageEnd()
        );
        return list;
    }

    @Override
    public boolean saveBatch(Collection<Object> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<Object> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<Object> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(Object entity) {
        return false;
    }

    @Override
    public Object getOne(Wrapper<Object> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<Object> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<Object> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<Object> getBaseMapper() {
        return null;
    }

    @Override
    public Class<Object> getEntityClass() {
        return null;
    }
}
