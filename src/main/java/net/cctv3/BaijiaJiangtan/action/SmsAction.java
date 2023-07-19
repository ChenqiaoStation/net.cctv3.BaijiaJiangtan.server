package net.cctv3.BaijiaJiangtan.action;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.cctv3.BaijiaJiangtan.bean.Sms;
import net.cctv3.BaijiaJiangtan.dao.CustomerDAO;
import net.cctv3.BaijiaJiangtan.dao.SmsDAO;
import net.cctv3.BaijiaJiangtan.service.SeriesService;
import net.cctv3.BaijiaJiangtan.x.LogUtils;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class SmsAction {
    LogUtils logUtils = new LogUtils(SmsAction.class);
    @Autowired
    SmsDAO smsDAO;
    @CrossOrigin
    @GetMapping(value = "/selectSMS.do")
    public String loginByPassword(@RequestParam(value = "mobile") String mobile) {
        HashMap<String, Object> hashMap = new HashMap<>();
        String code = StringUtils.useRandomCode(6);
        hashMap.put("data", code);
        Sms newSms = new Sms(StringUtils.useUUID(), mobile, code, StringUtils.useTimeFormatter(new Date()));
        QueryWrapper<Sms> smsQueryWrapper = new QueryWrapper<>();
        smsQueryWrapper.eq("mobile", mobile);
        Sms oldSms = smsDAO.selectOne(smsQueryWrapper);
        int rows = 0;
        if (oldSms == null) {
            rows = smsDAO.insert(newSms);
        } else {
            if (StringUtils.useIsInTimeRange(oldSms.getTime(), 60)) {
                hashMap.put("message", "验证码发送频繁 ~");
            } else {
                newSms.setId(oldSms.getId());
                rows = smsDAO.updateById(newSms);
            }
        }
        return StringUtils.response(rows == 1, hashMap);
    }
}