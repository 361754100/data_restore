package cn.com.nexwise.data_restore.threadpool;

import cn.com.nexwise.data_restore.listener.MacRecordTaskListener;
import cn.com.nexwise.data_restore.listener.TaskListener;
import cn.com.nexwise.data_restore.listener.ZmRecordTaskListener;
import cn.com.nexwise.data_restore.lock.DataSyncLockObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.concurrent.*;

/**
 * 通用的线程池任务管理器
 * @author Mojianzhang
 *
 */
public class CommonTaskManager {
	
	private static CommonTaskManager instance = null;
	
	private static final Logger logger = LoggerFactory.getLogger(CommonTaskManager.class);
	
	private final static int initialThreadSize = 20;
	private final static int maxThreadSize = 100;
	private final static int threadWaitingTimeoutMillis = 30000;
	private final static int backlogSize = 3000;
	
	private ThreadPoolExecutor taskExecutor = new ThreadPoolExecutor( initialThreadSize, maxThreadSize, threadWaitingTimeoutMillis,
			TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(backlogSize));


	private final static DataSyncLockObj zmSyncLock = new DataSyncLockObj();
	private final static DataSyncLockObj macSyncLock = new DataSyncLockObj();


	private static class HolderClass {
		private static final CommonTaskManager manager = new CommonTaskManager();
	}
	
	public static CommonTaskManager getInstance() {
		if(instance == null) {
			instance = HolderClass.manager;
		}
		return instance;
	}

	/**
	 * 添加任务
	 * @param task
	 */
	public void addTask(Runnable task) {
		logger.debug("[CommonTaskManager] Active Thread Count ->"+taskExecutor.getActiveCount()+"  maxThreadSize ->"+maxThreadSize);
		if (taskExecutor.getQueue().size() < backlogSize) {
			taskExecutor.execute(task);
		} else {
			logger.debug("CommonTaskManager backlogSize has reach too limit!!");
		}
	}

	/**
	 * 停止任务
	 */
	public void stop() {
		taskExecutor.getQueue().clear();
		taskExecutor.shutdownNow();
		logger.debug("<<< CommonTaskManager Executor shutdown >>>");
	}

	/**
	 * 设置任务锁
	 * @param taskCode
	 * @param targetState
	 */
	public void markZmTaskLock(String taskCode, int targetState, TaskListener taskListener) {
		zmSyncLock.setCode(taskCode);
		zmSyncLock.setState(0);
		zmSyncLock.setTargetState(targetState);
		zmSyncLock.setTaskListener(taskListener);
	}

	/**
	 * 设置任务状态
	 * @param taskCode
	 * @param state
	 */
	public synchronized void setZmLockState(String taskCode, int state) {
		zmSyncLock.setState(zmSyncLock.getState() + state);
		if(taskCode.equals(zmSyncLock.getCode()) && zmSyncLock.getState() == zmSyncLock.getTargetState()) {
			ZmRecordTaskListener taskListener = (ZmRecordTaskListener) zmSyncLock.getTaskListener();

			logger.info("zm sync task finished...taskCode = {}, pageNum = {}, pageSize = {}, pageCount = {}",
					new Object[]{zmSyncLock.getCode(), taskListener.getPageNum(), taskListener.getPageSize(), taskListener.getPageCount()});
			if(taskListener.getPageNum() > taskListener.getPageCount()) {
				logger.info("zm data sync finished...");
				return;
			}
			taskListener.callBack();
		}
	}

	/**
	 * 设置任务锁
	 * @param taskCode
	 * @param targetState
	 */
	public void markMacTaskLock(String taskCode, int targetState, TaskListener taskListener) {
		macSyncLock.setCode(taskCode);
		macSyncLock.setState(0);
		macSyncLock.setTargetState(targetState);
		macSyncLock.setTaskListener(taskListener);
	}

	/**
	 * 设置任务状态
	 * @param taskCode
	 * @param state
	 */
	public synchronized void setMacLockState(String taskCode, int state) {
		macSyncLock.setState(macSyncLock.getState() + state);
		if(taskCode.equals(macSyncLock.getCode()) && macSyncLock.getState() == macSyncLock.getTargetState()) {
			MacRecordTaskListener taskListener = (MacRecordTaskListener) macSyncLock.getTaskListener();

			logger.info("mac sync task finished...taskCode = {}, pageNum = {}, pageSize = {}, pageCount = {}",
					new Object[]{macSyncLock.getCode(), taskListener.getPageNum(), taskListener.getPageSize(), taskListener.getPageCount()});
			if(taskListener.getPageNum() > taskListener.getPageCount()) {
				logger.info("mac data sync finished...");
				return;
			}
			taskListener.callBack();
		}
	}
}
