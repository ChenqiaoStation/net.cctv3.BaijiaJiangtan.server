package net.cctv3.BaijiaJiangtan.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.cctv3.BaijiaJiangtan.x.HttpClientUtils;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class UseHttpClientAction {
    // https://apis.map.qq.com/ws/location/v1/ip?ip=110.138.175.15&key=PJCBZ-KPRWU-6VMVP-4EBRC-TRPCZ-LJBYT
    @CrossOrigin
    @GetMapping(value = "/useTencentIP2Address.action")
    public String useTencentIP2Address(HttpServletRequest request) {
        // System.out.println("useEastMoneyFundDetailById.action: " + id);
        String realIP = StringUtils.useRealAddress(request);
        String string = HttpClientUtils.doGet(
                String.format("https://apis.map.qq.com/ws/location/v1/ip?ip=%s&key=PJCBZ-KPRWU-6VMVP-4EBRC-TRPCZ-LJBYT",
                        realIP
                ),
                "utf-8"
        );
        HashMap<String, Object> hashMap = new HashMap<>();
        JSONObject root = JSON.parseObject(string);
        String address = "";
        if(root.getInteger("status") == 0) {
            hashMap.put("status", 1);
            JSONObject result = root.getJSONObject("result").getJSONObject("ad_info");
            StringBuilder builder = new StringBuilder();
            String nation = result.getString("nation");
            if(nation.equals("中国")) {
                String province = result.getString("province");
                String city = result.getString("city");
                String street = result.getString("district");
                if(province.equals(city)) {
                    builder.append(city).append(" ").append(street);
                }
                else {
                    builder.append(province).append(" ").append(city).append(" ").append(street);
                }
                address = builder.toString().trim();
            }
            else {
                address = nation;
            }
            hashMap.put("address", address);
        }
        else {
            hashMap.put("status", 0);
        }
        hashMap.put("IP", realIP);
        hashMap.put("agent", request.getHeader("user-agent"));
        String result = JSON.toJSONString(hashMap);
        System.out.println(result);
        return result;
    }
}