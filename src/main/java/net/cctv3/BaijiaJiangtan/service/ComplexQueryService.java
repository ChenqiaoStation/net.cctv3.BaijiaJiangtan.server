package net.cctv3.BaijiaJiangtan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.cctv3.BaijiaJiangtan.bean.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ComplexQueryService extends IService<Object> {
    List<SelectSearchChapter> selectSearchChapters(String keywords, int pageIndex, int pageSize);
    List<SelectSearchSeries> selectSearchSerieses(String keywords, int pageIndex, int pageSize);
    List<SelectSearchTeacher> selectSearchTeachers(String keywords, int pageIndex, int pageSize);
    List<SelectTeacherAndCount> selectTeachersAndCounts(int pageIndex, int pageSize);
}