package cn.com.nexwise.data_restore.threadpool;

import cn.com.nexwise.data_restore.common.SpringContext;
import cn.com.nexwise.data_restore.service.DataSyncService;

public class MacSyncBeginTask implements Runnable{

    @Override
    public void run() {
        DataSyncService dataSyncService = SpringContext.getBean(DataSyncService.class);
        dataSyncService.beginMacSync();
    }
}
