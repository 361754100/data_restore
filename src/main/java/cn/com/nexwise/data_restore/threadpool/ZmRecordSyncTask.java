package cn.com.nexwise.data_restore.threadpool;

import cn.com.nexwise.data_restore.common.SpringContext;
import cn.com.nexwise.data_restore.dao.mongo.ZmRecordSrcDao;
import cn.com.nexwise.data_restore.dao.mongo.ZmRecordTgtDao;
import cn.com.nexwise.data_restore.dao.mongo.entites.ZmRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 侦码数据同步任务
 */
public class ZmRecordSyncTask implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(ZmRecordSyncTask.class);

    private int pageSize;

    private int skipNum;

    private String taskCode;

    public ZmRecordSyncTask(String taskCode, int pageSize, int skipNum) {
        this.taskCode = taskCode;
        this.pageSize = pageSize;
        this.skipNum = skipNum;
    }

    @Override
    public void run() {
        ZmRecordSrcDao zmRecordSrcDao = SpringContext.getBean(ZmRecordSrcDao.class);
        ZmRecordTgtDao zmRecordTgtDao = SpringContext.getBean(ZmRecordTgtDao.class);
        List<ZmRecord> srcRecords = zmRecordSrcDao.getPageList(pageSize, skipNum);

        int pageNum = (skipNum/pageSize)+1;
        if(CollectionUtils.isEmpty(srcRecords)) {
            logger.info("page is empty...pageNum = {}, pageSize = {}", pageNum, pageSize);
            return;
        }
        try {
            zmRecordTgtDao.saveList(srcRecords);
            logger.info("page data save finished...pageNum = {}, pageSize = {}", pageNum, pageSize);
            CommonTaskManager.getInstance().setZmLockState(taskCode,1);
        } catch (Exception e) {
            logger.error("page data save error...pageNum = {}, pageSize = {}", pageNum, pageSize);
        }

    }
}
