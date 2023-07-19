package net.cctv3.BaijiaJiangtan.action;

import com.alibaba.fastjson.JSON;
import net.cctv3.BaijiaJiangtan.bean.Test;
import net.cctv3.BaijiaJiangtan.dao.TestDAO;
import net.cctv3.BaijiaJiangtan.x.LogUtils;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class TestAction {
    LogUtils logUtils = new LogUtils(TestAction.class);
    @Autowired TestDAO testDAO;
    @CrossOrigin
    @GetMapping(value = "/selectTest.do")
    public String selectTest(@RequestParam(value = "id", required = true) int id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        Test test = testDAO.selectTest(id);
        if(test == null) {
            hashMap.put("status", 0);
        }
        else {
            hashMap.put("status", 1);
            hashMap.put("data", test);
        }
        logUtils.log("selectTest: {}", new Object[]{hashMap});
        return JSON.toJSONString(hashMap);
    }

    @CrossOrigin
    @PostMapping("/testParamsByJSON.do")
    public String paramsByJSON(@RequestBody Test test) {
        /** Postman → raw 传参 */
        return JSON.toJSONString(test);
    }

    @CrossOrigin
    @PostMapping("/testParamsByObject.do")
    public String paramsByObject(Test test) {
        /** Postman → form-data 传参 */
        return JSON.toJSONString(test);
    }

    @CrossOrigin
    @GetMapping("/testRandomGenerator.do")
    public String paramsByObject(@RequestParam(value = "n") int n) {
        /** Postman → form-data 传参 */
        HashMap hashMap = new HashMap();
        hashMap.put("data", StringUtils.useRandomCode(n));
        return StringUtils.response(true, hashMap);
    }
}