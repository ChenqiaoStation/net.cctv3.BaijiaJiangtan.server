package net.cctv3.BaijiaJiangtan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cctv3.BaijiaJiangtan.bean.Chapter;
import net.cctv3.BaijiaJiangtan.bean.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChapterDAO extends BaseMapper<Chapter> {
    Chapter selectChapterByCCTVID(String uuid);
    List<Chapter> selectChaptersByStatus(int status);
    List<Chapter> selectChaptersBySeries(String id);
    Chapter selectChapter(String id);
    int updateChapterTitleByCCTVID(String cctv, String title);
}