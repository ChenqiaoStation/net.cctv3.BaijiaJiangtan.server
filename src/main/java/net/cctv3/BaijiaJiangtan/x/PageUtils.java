package net.cctv3.BaijiaJiangtan.x;

public class PageUtils {
    private int pageIndex;
    private int pageSize;

    public PageUtils(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public int getPageStart() {
        return (pageIndex - 1) * pageSize;
    }

    public int getPageEnd() {
        return pageSize;
    }
}
