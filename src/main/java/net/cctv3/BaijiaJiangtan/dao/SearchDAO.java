package net.cctv3.BaijiaJiangtan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cctv3.BaijiaJiangtan.bean.Search;
import net.cctv3.BaijiaJiangtan.bean.SimpleMap;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchDAO extends BaseMapper<Search> {
    List<SimpleMap> selectSearchesByCounts(Integer limits);
}