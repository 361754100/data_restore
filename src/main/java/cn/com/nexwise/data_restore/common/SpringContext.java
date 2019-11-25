package cn.com.nexwise.data_restore.common;

import cn.com.nexwise.data_restore.threadpool.CommonTaskManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * Spring 上下文工具类
 * @author Mojianzhang
 */
@Component
public class SpringContext implements ApplicationContextAware, DisposableBean, ExitCodeGenerator {

    private static ApplicationContext applicationContext;

    private static Logger logger = LoggerFactory.getLogger(SpringContext.class);

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringContext.applicationContext == null) {
            SpringContext.applicationContext = applicationContext;
        }

        logger.info("=== ApplicationContext 配置成功,在普通类可以通过调用 SpringContext.getAppContext() 获取上下文对象,applicationContext="+ SpringContext.applicationContext +" ===");
    }

    //获取applicationContext
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    //通过name获取 Bean.
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    //通过class获取Bean.
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    //通过name,以及Clazz返回指定的Bean
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }




    @Override
    public void destroy() throws Exception {
        logger.debug("spring boot destroy...");
        CommonTaskManager.getInstance().stop();
    }

    @Override
    public int getExitCode() {
        return 5;
    }
}
