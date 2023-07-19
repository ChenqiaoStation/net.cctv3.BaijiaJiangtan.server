package net.cctv3.BaijiaJiangtan.x;

import com.alibaba.fastjson.JSON;
import net.cctv3.BaijiaJiangtan.BaijiaJiangtanApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StringUtils {
    public static String useRealAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的 IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        // 对于通过多个代理的情况，第一个 IP 为客户端真实 IP,多个 IP 按照 ',' 分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    public static String useUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String useTimeFormatter(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    /**
     * ProTable 需要的数据格式
     * @param success
     * @param list
     * @param total
     * @return https://procomponents.ant.design/components/table
     */
    public static String response(Boolean success, List list, int total) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("success", success);
        hashMap.put("data", list);
        hashMap.put("total", total);
        return JSON.toJSONString(hashMap);
    }

    /**
     * ProTable 需要的数据格式
     * @param success
     * @param object
     * @return https://procomponents.ant.design/components/table
     */
    public static String response(Boolean success, Object object) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("success", success);
        hashMap.put("data", object);
        return JSON.toJSONString(hashMap);
    }

    /**
     * 普通的提交，无 Data
     * @param success
     * @return
     */
    public static String response(Boolean success) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("success", success);
        return JSON.toJSONString(hashMap);
    }

    public static String useRandomCode(int n) {
        String result = "";
        long min = (long) Math.pow(10, n-1);
        long max = (long) Math.pow(10, n) - 1;
        Random random = new Random();
        long number = 0;
        do {
            number = (long) (random.nextDouble() * Math.pow(10, n));
        } while (Math.abs(number) < min || Math.abs(number) > max);
        return number + "";
    }

    /**
     * 开始时间和当前的时间差是否在 `${range}s` 范围内
     * @param time 开始时间
     * @param range 秒
     * @return
     */
    public static boolean useIsInTimeRange(String time, int range) {
        boolean result = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            long start = dateFormat.parse(time).getTime();
            long end = System.currentTimeMillis();
            result = end - start < range * 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * MyBatisPlus 分页 wrapper.last( ... )
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static String usePage(int pageIndex, int pageSize) {
        return String.format("limit %d, %d", pageSize * (pageIndex-1), pageSize);
    }
}