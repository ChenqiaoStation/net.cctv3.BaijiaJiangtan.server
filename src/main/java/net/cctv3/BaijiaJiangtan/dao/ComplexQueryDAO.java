package net.cctv3.BaijiaJiangtan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cctv3.BaijiaJiangtan.bean.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ComplexQueryDAO extends BaseMapper {
    List<SelectSeriesesByTeacher> selectSeriesesByTeacher(String id);
    List<SelectSearchChapter> selectSearchChapters(String keywords, int m, int n);
    List<SelectSearchSeries> selectSearchSerieses(String keywords, int m, int n);
    List<SelectSearchTeacher> selectSearchTeachers(String keywords, int m, int n);
    List<SelectTeacherAndCount> selectTeachersAndCounts(int m, int n);
}