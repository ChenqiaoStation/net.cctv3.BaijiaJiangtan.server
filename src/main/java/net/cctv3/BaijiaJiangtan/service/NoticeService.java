package net.cctv3.BaijiaJiangtan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.cctv3.BaijiaJiangtan.bean.Notice;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NoticeService extends IService<Notice> {
    List<Notice> selectNotices(Integer status, Integer limits);
}