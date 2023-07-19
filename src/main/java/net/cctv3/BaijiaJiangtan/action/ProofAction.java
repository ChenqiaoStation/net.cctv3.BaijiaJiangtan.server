package net.cctv3.BaijiaJiangtan.action;

import com.alibaba.fastjson.JSON;
import net.cctv3.BaijiaJiangtan.bean.Proof;
import net.cctv3.BaijiaJiangtan.bean.Test;
import net.cctv3.BaijiaJiangtan.dao.ProofDAO;
import net.cctv3.BaijiaJiangtan.dao.TestDAO;
import net.cctv3.BaijiaJiangtan.x.LogUtils;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@RestController
public class ProofAction {
    LogUtils logUtils = new LogUtils(ProofAction.class);
    @Autowired
    ProofDAO proofDAO;
    @CrossOrigin
    @PostMapping ("/insertProof.do")
    public String insertProof(@RequestBody Proof proof) {
        /**
         * @RequestParam("proof") Proof proof 估计外面要用 proof 包裹一下
         * */
        HashMap<String, Object> hashMap = new HashMap<>();
        proof.setId(StringUtils.useUUID());
        proof.setTime(StringUtils.useTimeFormatter(new Date()));
        logUtils.log("insertProof: {}", new Object[]{proof});
        int status = proofDAO.insertProof(proof);
        hashMap.put("status", status);
        return JSON.toJSONString(hashMap);
    }
}