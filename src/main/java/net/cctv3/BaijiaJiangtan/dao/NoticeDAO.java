package net.cctv3.BaijiaJiangtan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cctv3.BaijiaJiangtan.bean.Notice;
import net.cctv3.BaijiaJiangtan.bean.SelectSeriesByYear;
import net.cctv3.BaijiaJiangtan.bean.Series;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeDAO extends BaseMapper<Notice> {
    List<Series> selectNotices();
}