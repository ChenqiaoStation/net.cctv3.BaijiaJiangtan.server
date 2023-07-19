package net.cctv3.BaijiaJiangtan.action;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import net.cctv3.BaijiaJiangtan.bean.Customer;
import net.cctv3.BaijiaJiangtan.bean.Sms;
import net.cctv3.BaijiaJiangtan.dao.CustomerDAO;
import net.cctv3.BaijiaJiangtan.dao.SmsDAO;
import net.cctv3.BaijiaJiangtan.x.LogUtils;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RestController
public class CustomerAction {
    LogUtils logUtils = new LogUtils(CustomerAction.class);
    @Autowired
    CustomerDAO customerDAO;
    @Autowired
    SmsDAO smsDAO;

    @CrossOrigin
    @GetMapping(value = "/loginByMobile.do")
    public String loginByMobile(@RequestParam(value = "mobile") String mobile, @RequestParam(value = "code") String code) {
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean success = false;
        QueryWrapper<Sms> smsWrapper = new QueryWrapper<>();
        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();

        smsWrapper.eq("mobile", mobile).eq("code", code);
        Sms sms = smsDAO.selectOne(smsWrapper);
        if (sms == null || !sms.getCode().equals(code)) {
            hashMap.put("message", "验证码错误 ~");
        } else if (StringUtils.useIsInTimeRange(sms.getTime(), 120)) {
            customerQueryWrapper.eq("mobile", mobile);
            Customer oldCustomer = customerDAO.selectOne(customerQueryWrapper);
            if (oldCustomer == null) {
                /** 注册 */
                String uniqueId = "";
                do {
                    uniqueId = StringUtils.useRandomCode(8);
                    customerQueryWrapper.clear();
                    customerQueryWrapper.eq("unique_id", uniqueId);
                } while (customerDAO.selectOne(customerQueryWrapper) != null);
                Customer customer = new Customer(StringUtils.useUUID(),
                        uniqueId,
                        mobile,
                        "用户" + uniqueId,
                        "",
                        "",
                        0,
                        String.format("https://net-cctv3.oss-cn-qingdao.aliyuncs.com/net.cctv3.BaijiaJiangtan/Avatar/Duole-%d.jpg", (int) (Math.random() * 15 + 1)),
                        "",
                        "",
                        StringUtils.useTimeFormatter(new Date()),
                        StringUtils.useTimeFormatter(new Date()),
                        "",
                        1
                );
                success = customerDAO.insert(customer) == 1;
                hashMap.put("data", customer);
            } else {
                /** 登录 */
                oldCustomer.setUpdateTime(StringUtils.useTimeFormatter(new Date()));
                success = customerDAO.updateById(oldCustomer) == 1;
                hashMap.put("data", oldCustomer);
            }
        } else {
            hashMap.put("message", "验证码已过期 ~");
        }
        return StringUtils.response(success, hashMap);
    }

    @CrossOrigin
    @GetMapping(value = "/loginByPassword.do")
    public String loginByPassword(@RequestParam(value = "mobile") String mobile, @RequestParam(value = "password") String password) {
        QueryWrapper<Customer> customerQueryWrapper = new QueryWrapper<>();
        customerQueryWrapper.eq("mobile", mobile);
        Customer customer = customerDAO.selectOne(customerQueryWrapper);
        HashMap<String, Object> hashMap = new HashMap<>();
        boolean success = false;
        if (customer == null || customer.getPassword().equals("") || !customer.getPassword().equals(password)) {
            hashMap.put("message", "用户不存在或者密码错误 ~");
        } else {
            success = true;
            hashMap.put("data", customer);
        }
        return StringUtils.response(success, hashMap);
    }
}