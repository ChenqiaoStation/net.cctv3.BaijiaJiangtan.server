package net.cctv3.BaijiaJiangtan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cctv3.BaijiaJiangtan.bean.Customer;
import net.cctv3.BaijiaJiangtan.bean.Sms;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmsDAO extends BaseMapper<Sms> {

}