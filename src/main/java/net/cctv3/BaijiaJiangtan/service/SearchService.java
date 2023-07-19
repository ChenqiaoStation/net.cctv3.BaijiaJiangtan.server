package net.cctv3.BaijiaJiangtan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.cctv3.BaijiaJiangtan.bean.Search;
import net.cctv3.BaijiaJiangtan.bean.SimpleMap;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SearchService extends IService<Search> {
    List<Search> selectSearchesByCustomer(String id, Integer limits, Integer status);
    List<SimpleMap> selectSearchesByCounts(Integer limits);
}