package net.cctv3.BaijiaJiangtan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cctv3.BaijiaJiangtan.bean.Discuss;
import net.cctv3.BaijiaJiangtan.bean.SelectDiscuss;
import net.cctv3.BaijiaJiangtan.bean.Sms;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DiscussDAO extends BaseMapper<Discuss> {
    List<SelectDiscuss> selectDiscussesById(String id);
}