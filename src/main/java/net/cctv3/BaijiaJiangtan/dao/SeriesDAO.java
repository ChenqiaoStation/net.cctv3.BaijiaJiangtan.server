package net.cctv3.BaijiaJiangtan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cctv3.BaijiaJiangtan.bean.Proof;
import net.cctv3.BaijiaJiangtan.bean.SelectSeriesByYear;
import net.cctv3.BaijiaJiangtan.bean.Series;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SeriesDAO extends BaseMapper<Series> {
    List<Series> findSerieses();
    List<SelectSeriesByYear> selectSeriesesByYear(int year);
    List<Series> selectLatest10UpdatedSerieses();
    List<Series> selectShuffleSerieses(int total);
}