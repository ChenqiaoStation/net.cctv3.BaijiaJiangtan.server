package net.cctv3.BaijiaJiangtan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cctv3.BaijiaJiangtan.bean.Test;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestDAO extends BaseMapper<Test> {
    Test selectTest(int id);
}