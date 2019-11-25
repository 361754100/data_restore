package cn.com.nexwise.data_restore.listener;

import cn.com.nexwise.data_restore.common.SpringContext;
import cn.com.nexwise.data_restore.service.DataSyncService;

/**
 * zmrecord同步任务监听器
 */
public class ZmRecordTaskListener implements TaskListener {

    private int pageNum;
    private int pageSize;
    private int pageCount;

    public ZmRecordTaskListener(int pageNum, int pageSize, int pageCount) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
    }

    @Override
    public void callBack() {
        DataSyncService dataSyncService = SpringContext.getBean(DataSyncService.class);
        dataSyncService.handleZmSync(pageNum, pageSize, pageCount);
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}
