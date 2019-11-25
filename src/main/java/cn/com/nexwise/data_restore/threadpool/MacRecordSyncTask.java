package cn.com.nexwise.data_restore.threadpool;

import cn.com.nexwise.data_restore.common.SpringContext;
import cn.com.nexwise.data_restore.dao.mongo.MacRecordSrcDao;
import cn.com.nexwise.data_restore.dao.mongo.MacRecordTgtDao;
import cn.com.nexwise.data_restore.dao.mongo.ZmRecordSrcDao;
import cn.com.nexwise.data_restore.dao.mongo.ZmRecordTgtDao;
import cn.com.nexwise.data_restore.dao.mongo.entites.MacRecord;
import cn.com.nexwise.data_restore.dao.mongo.entites.ZmRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Mac数据同步任务
 */
public class MacRecordSyncTask implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(MacRecordSyncTask.class);

    private int pageSize;

    private int skipNum;

    private String taskCode;

    public MacRecordSyncTask(String taskCode, int pageSize, int skipNum) {
        this.taskCode = taskCode;
        this.pageSize = pageSize;
        this.skipNum = skipNum;
    }

    @Override
    public void run() {
        MacRecordSrcDao macRecordSrcDao = SpringContext.getBean(MacRecordSrcDao.class);
        MacRecordTgtDao macRecordTgtDao = SpringContext.getBean(MacRecordTgtDao.class);
        List<MacRecord> srcRecords = macRecordSrcDao.getPageList(pageSize, skipNum);

        int pageNum = (skipNum/pageSize)+1;
        if(CollectionUtils.isEmpty(srcRecords)) {
            logger.info("page is empty...pageNum = {}, pageSize = {}", pageNum, pageSize);
            return;
        }
        try {
            macRecordTgtDao.saveList(srcRecords);
            logger.info("page data save finished...pageNum = {}, pageSize = {}", pageNum, pageSize);
            CommonTaskManager.getInstance().setMacLockState(taskCode,1);
        } catch (Exception e) {
            logger.error("page data save error...pageNum = {}, pageSize = {}", pageNum, pageSize);
        }

    }
}
