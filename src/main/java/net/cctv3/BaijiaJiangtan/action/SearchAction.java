package net.cctv3.BaijiaJiangtan.action;

import net.cctv3.BaijiaJiangtan.bean.Search;
import net.cctv3.BaijiaJiangtan.bean.SimpleMap;
import net.cctv3.BaijiaJiangtan.dao.SearchDAO;
import net.cctv3.BaijiaJiangtan.service.SearchService;
import net.cctv3.BaijiaJiangtan.x.LogUtils;
import net.cctv3.BaijiaJiangtan.x.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SearchAction {
    LogUtils logUtils = new LogUtils(SearchAction.class);
    @Autowired
    SearchDAO searchDAO;
    @Autowired
    SearchService searchService;

    @CrossOrigin
    @PostMapping("/mergeSearch.do")
    public String mergeSearch(@RequestBody Search search) {
        logUtils.log("mergeSearch: {}", new Object[]{search});
        boolean success = searchService.saveOrUpdate(search);
        return StringUtils.response(success);
    }

    @CrossOrigin
    @GetMapping("/selectSearchesByCustomer.do")
    public String selectSearchesByCustomer(
            @RequestParam(value = "id", required = true) String id,
            @RequestParam(value = "limits", required = false) Integer limits,
            @RequestParam(value = "status", required = false) Integer status
    ) {
        List<Search> list = searchService.selectSearchesByCustomer(id, limits, status);
        return StringUtils.response(true, list, list.size());
    }

    @CrossOrigin
    @GetMapping("/selectSearchesByCounts.do")
    public String selectSearchesByCounts(
            @RequestParam(value = "limits", required = false) Integer limits
    ) {
        List<SimpleMap> list = searchService.selectSearchesByCounts(limits);
        return StringUtils.response(true, list, list.size());
    }

    @CrossOrigin
    @GetMapping("/deleteSearch.do")
    public String deleteTeacher(@RequestParam(value = "id") String id) {
        boolean success = searchService.removeById(id);
        return StringUtils.response(success);
    }
}