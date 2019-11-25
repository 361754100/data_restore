package cn.com.nexwise.data_restore;

import cn.com.nexwise.data_restore.threadpool.CommonTaskManager;
import cn.com.nexwise.data_restore.threadpool.MacSyncBeginTask;
import cn.com.nexwise.data_restore.threadpool.ZmSyncBeginTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataRestoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataRestoreApplication.class, args);

        ZmSyncBeginTask zmSyncBeginTask = new ZmSyncBeginTask();
        MacSyncBeginTask macSyncBeginTask = new MacSyncBeginTask();

        CommonTaskManager.getInstance().addTask(zmSyncBeginTask);
        CommonTaskManager.getInstance().addTask(macSyncBeginTask);
    }

}
