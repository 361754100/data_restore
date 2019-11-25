package cn.com.nexwise.data_restore.service;

import cn.com.nexwise.data_restore.config.CommonConfig;
import cn.com.nexwise.data_restore.dao.mongo.MacRecordSrcDao;
import cn.com.nexwise.data_restore.dao.mongo.ZmRecordSrcDao;
import cn.com.nexwise.data_restore.listener.MacRecordTaskListener;
import cn.com.nexwise.data_restore.listener.ZmRecordTaskListener;
import cn.com.nexwise.data_restore.threadpool.CommonTaskManager;
import cn.com.nexwise.data_restore.threadpool.MacRecordSyncTask;
import cn.com.nexwise.data_restore.threadpool.ZmRecordSyncTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataSyncService {

    private static Logger logger = LoggerFactory.getLogger(DataSyncService.class);

    @Autowired
    private CommonConfig commonConfig;

    @Autowired
    private ZmRecordSrcDao zmRecordSrcDao;

    @Autowired
    private MacRecordSrcDao macRecordSrcDao;

    /**
     * 开始侦码数据同步
     */
    public void beginZmSync() {
        long srcCount = zmRecordSrcDao.count();
        int batchSize = commonConfig.getZmSyncBatchSize();

        long pageCount = srcCount % batchSize == 0? srcCount/batchSize: srcCount/batchSize +1;
        int pageSize = batchSize;
        int pageNum = commonConfig.getZmSyncBeginPageNum();

        logger.info("begin zm sync...pageNum={}, pageSize={}, pageCount={}", new Object[]{pageNum, pageSize, pageCount});
        this.handleZmSync(pageNum, pageSize, (int)pageCount);
    }

    /**
     * 多线程执行侦码数据同步
     * @param pageNum
     * @param pageSize
     * @param pageCount
     */
    public void handleZmSync(int pageNum, int pageSize, int pageCount) {
        String taskCode = UUID.randomUUID().toString();
        int threadCount = commonConfig.getZmSyncThreadCount();

        ZmRecordTaskListener listener = new ZmRecordTaskListener(pageNum + threadCount, pageSize, pageCount);
        CommonTaskManager.getInstance().markZmTaskLock(taskCode, threadCount, listener);

        for(int i = 0; i< threadCount; i++) {
            ZmRecordSyncTask zmSyncTask = new ZmRecordSyncTask(taskCode, pageSize, (pageNum + i - 1)*pageSize);
            CommonTaskManager.getInstance().addTask(zmSyncTask);
        }
    }

    /**
     * 开始侦码数据同步
     */
    public void beginMacSync() {
        long srcCount = macRecordSrcDao.count();
        int batchSize = commonConfig.getMacSyncBatchSize();

        long pageCount = srcCount % batchSize == 0? srcCount/batchSize: srcCount/batchSize +1;
        int pageSize = batchSize;
        int pageNum = commonConfig.getMacSyncBeginPageNum();

        logger.info("begin mac sync...pageNum={}, pageSize={}, pageCount={}", new Object[]{pageNum, pageSize, pageCount});
        this.handleMacSync(pageNum, pageSize, (int)pageCount);
    }

    /**
     * 多线程执行侦码数据同步
     * @param pageNum
     * @param pageSize
     * @param pageCount
     */
    public void handleMacSync(int pageNum, int pageSize, int pageCount) {
        String taskCode = UUID.randomUUID().toString();
        int threadCount = commonConfig.getMacSyncThreadCount();

        MacRecordTaskListener listener = new MacRecordTaskListener(pageNum + threadCount, pageSize, pageCount);
        CommonTaskManager.getInstance().markMacTaskLock(taskCode, threadCount, listener);

        for(int i = 0; i< threadCount; i++) {
            MacRecordSyncTask macSyncTask = new MacRecordSyncTask(taskCode, pageSize, (pageNum + i - 1)*pageSize);
            CommonTaskManager.getInstance().addTask(macSyncTask);
        }

    }

}
