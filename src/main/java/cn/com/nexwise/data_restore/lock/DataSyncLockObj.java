package cn.com.nexwise.data_restore.lock;

import cn.com.nexwise.data_restore.listener.TaskListener;

/**
 * 数据同步的锁对象
 */
public class DataSyncLockObj {

    private String code = "";

    private volatile int state = 0;

    private int targetState = -1;

    private TaskListener taskListener;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void stateMark() {
        this.state ++;
    }

    public int getTargetState() {
        return targetState;
    }

    public void setTargetState(int targetState) {
        this.targetState = targetState;
    }

    public TaskListener getTaskListener() {
        return taskListener;
    }

    public void setTaskListener(TaskListener taskListener) {
        this.taskListener = taskListener;
    }
}
