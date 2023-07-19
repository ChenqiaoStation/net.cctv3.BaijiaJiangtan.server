package net.cctv3.BaijiaJiangtan.x;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
    private Class myClass = null;

    public LogUtils(Class myClass) {
        this.myClass = myClass;
    }

    public void log(String message, Object[] arguments) {
        Logger logger = LoggerFactory.getLogger(myClass);
        logger.info(String.format("HelloWorld → %s", message), arguments);
    }
}
