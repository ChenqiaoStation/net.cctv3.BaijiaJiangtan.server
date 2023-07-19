package net.cctv3.BaijiaJiangtan.action;

import net.cctv3.BaijiaJiangtan.bean.Discuss;
import net.cctv3.BaijiaJiangtan.dao.DiscussDAO;
import net.cctv3.BaijiaJiangtan.x.LogUtils;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

@RestController
public class DiscussAction {
    LogUtils logUtils = new LogUtils(DiscussAction.class);
    @Autowired
    DiscussDAO discussDAO;

    @CrossOrigin
    @PostMapping(value = "/insertDiscuss.do")
    public String loginByPassword(@RequestBody Discuss discuss, HttpServletRequest request) {
        HashMap<String, Object> hashMap = new HashMap<>();
        discuss.setId(StringUtils.useUUID());
        discuss.setTime(StringUtils.useTimeFormatter(new Date()));
        int rows = discussDAO.insert(discuss);
        hashMap.put("data", discuss);
        return StringUtils.response(rows == 1, hashMap);
    }

    @CrossOrigin
    @GetMapping(value = "/selectDiscussesById.do")
    public String selectDiscussesById(@RequestParam(value = "id") String id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("data", discussDAO.selectDiscussesById(id));
        return StringUtils.response(true, hashMap);
    }
}