package net.cctv3.BaijiaJiangtan.action;

import net.cctv3.BaijiaJiangtan.bean.*;
import net.cctv3.BaijiaJiangtan.dao.ComplexQueryDAO;
import net.cctv3.BaijiaJiangtan.service.ComplexQueryService;
import net.cctv3.BaijiaJiangtan.x.LogUtils;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class ComplexQueryAction {
    LogUtils logUtils = new LogUtils(ComplexQueryAction.class);
    @Autowired
    ComplexQueryDAO complexQueryDAO;
    @Autowired
    ComplexQueryService complexQueryService;

    @CrossOrigin
    @GetMapping("/selectSeriesesByTeacher.do")
    public String selectSeriesesByTeacher(@RequestParam(value = "id") String id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        List<SelectSeriesesByTeacher> list = complexQueryDAO.selectSeriesesByTeacher(id);
        hashMap.put("data", list);
        return StringUtils.response(true, hashMap);
    }

    @GetMapping("/selectSearchChapters.do")
    public String selectSearchChapters(
            @RequestParam(value = "keywords") String keywords,
            @RequestParam(value = "pageIndex") int pageIndex,
            @RequestParam(value = "pageSize") int pageSize
    ) {
        List<SelectSearchChapter> list = complexQueryService.selectSearchChapters(keywords, pageIndex, pageSize);
        return StringUtils.response(true, list);
    }

    @GetMapping("/selectSearchSerieses.do")
    public String selectSearchSerieses(
            @RequestParam(value = "keywords") String keywords,
            @RequestParam(value = "pageIndex") int pageIndex,
            @RequestParam(value = "pageSize") int pageSize
    ) {
        List<SelectSearchSeries> list = complexQueryService.selectSearchSerieses(keywords, pageIndex, pageSize);
        return StringUtils.response(true, list);
    }

    @GetMapping("/selectSearchTeachers.do")
    public String selectSearchTeachers(
            @RequestParam(value = "keywords") String keywords,
            @RequestParam(value = "pageIndex") int pageIndex,
            @RequestParam(value = "pageSize") int pageSize
    ) {
        List<SelectSearchTeacher> list = complexQueryService.selectSearchTeachers(keywords, pageIndex, pageSize);
        return StringUtils.response(true, list);
    }

    @CrossOrigin
    @GetMapping("/selectTeachersAndCounts.do")
    public String selectTeachersAndCounts(
            @RequestParam(value = "pageIndex") int pageIndex,
            @RequestParam(value = "pageSize") int pageSize
    ) {
        List<SelectTeacherAndCount> teachers = complexQueryService.selectTeachersAndCounts(pageIndex, pageSize);
        return StringUtils.response(true, teachers, teachers.size());
    }
}