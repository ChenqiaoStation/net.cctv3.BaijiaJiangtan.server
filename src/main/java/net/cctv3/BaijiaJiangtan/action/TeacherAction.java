package net.cctv3.BaijiaJiangtan.action;

import net.cctv3.BaijiaJiangtan.bean.SelectTeacherAndCount;
import net.cctv3.BaijiaJiangtan.bean.Teacher;
import net.cctv3.BaijiaJiangtan.dao.TeacherDAO;
import net.cctv3.BaijiaJiangtan.service.TeacherService;
import net.cctv3.BaijiaJiangtan.x.LogUtils;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherAction {
    LogUtils logUtils = new LogUtils(TeacherAction.class);
    @Autowired
    TeacherDAO teacherDAO;
    @Autowired
    TeacherService teacherService;
    @CrossOrigin
    @PostMapping ("/mergeTeacher.do")
    public String mergeTeacher(@RequestBody Teacher teacher) {
        boolean success = teacherService.saveOrUpdate(teacher);
        logUtils.log("mergeTeacher: {}", new Object[]{teacher});
        return StringUtils.response(success);
    }

    @CrossOrigin
    @GetMapping ("/deleteTeacher.do")
    public String deleteTeacher(@RequestParam(value = "id") String id) {
        boolean success = teacherService.removeById(id);
        return StringUtils.response(success);
    }

    @CrossOrigin
    @GetMapping ("/selectTeachers.do")
    public String selectTeachers() {
        List<Teacher> teachers = teacherDAO.findTeachers();
        return StringUtils.response(true, teachers, teachers.size());
    }

    @CrossOrigin
    @GetMapping ("/selectTeacher.do")
    public String selectTeacher(@RequestParam(value = "id") String id) {
        Teacher teacher = teacherDAO.selectTeacher(id);
        return StringUtils.response(true, teacher);
    }
}