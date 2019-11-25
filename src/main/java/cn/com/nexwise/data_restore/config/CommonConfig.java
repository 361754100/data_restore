package cn.com.nexwise.data_restore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    @Value("${data.zm.sync.batchSize}")
    private int zmSyncBatchSize;

    @Value("${data.zm.sync.threadCount}")
    private int zmSyncThreadCount;

    @Value("${data.zm.sync.beginPageNum}")
    private int zmSyncBeginPageNum;

    @Value("${data.mac.sync.batchSize}")
    private int macSyncBatchSize;

    @Value("${data.mac.sync.threadCount}")
    private int macSyncThreadCount;

    @Value("${data.mac.sync.beginPageNum}")
    private int macSyncBeginPageNum;

    public int getZmSyncBatchSize() {
        return zmSyncBatchSize;
    }

    public void setZmSyncBatchSize(int zmSyncBatchSize) {
        this.zmSyncBatchSize = zmSyncBatchSize;
    }

    public int getZmSyncThreadCount() {
        return zmSyncThreadCount;
    }

    public void setZmSyncThreadCount(int zmSyncThreadCount) {
        this.zmSyncThreadCount = zmSyncThreadCount;
    }

    public int getZmSyncBeginPageNum() {
        return zmSyncBeginPageNum;
    }

    public void setZmSyncBeginPageNum(int zmSyncBeginPageNum) {
        this.zmSyncBeginPageNum = zmSyncBeginPageNum;
    }

    public int getMacSyncBatchSize() {
        return macSyncBatchSize;
    }

    public void setMacSyncBatchSize(int macSyncBatchSize) {
        this.macSyncBatchSize = macSyncBatchSize;
    }

    public int getMacSyncThreadCount() {
        return macSyncThreadCount;
    }

    public void setMacSyncThreadCount(int macSyncThreadCount) {
        this.macSyncThreadCount = macSyncThreadCount;
    }

    public int getMacSyncBeginPageNum() {
        return macSyncBeginPageNum;
    }

    public void setMacSyncBeginPageNum(int macSyncBeginPageNum) {
        this.macSyncBeginPageNum = macSyncBeginPageNum;
    }
}
