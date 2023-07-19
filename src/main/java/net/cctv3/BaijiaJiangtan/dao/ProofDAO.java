package net.cctv3.BaijiaJiangtan.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.cctv3.BaijiaJiangtan.bean.Proof;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProofDAO extends BaseMapper<Proof> {
    int insertProof(Proof proof);
}