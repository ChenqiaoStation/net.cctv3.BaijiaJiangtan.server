package net.cctv3.BaijiaJiangtan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cctv3.BaijiaJiangtan.bean.SelectTeacherAndCount;
import net.cctv3.BaijiaJiangtan.bean.SelectSeriesesByTeacher;
import net.cctv3.BaijiaJiangtan.bean.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherDAO extends BaseMapper<Teacher> {
    List<Teacher> findTeachers();
    List<SelectTeacherAndCount> selectTeachersAndCounts();
    Teacher selectTeacher(String id);
    List<SelectSeriesesByTeacher> selectTeachersSerieses(String id);
}