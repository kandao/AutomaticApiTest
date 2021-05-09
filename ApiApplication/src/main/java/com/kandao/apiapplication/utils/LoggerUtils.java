package com.kandao.apiapplication.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtils {

    private static final Logger logger = Logger.getLogger(LoggerUtils.class.getName());

    public void error(String errorMsg) {
        logger.log(Level.SEVERE, errorMsg);
    }

    public void info(String infoMsg) {
        logger.log(Level.INFO, infoMsg);
    }

    public void warn(String warnMsg) {
        logger.log(Level.WARNING, warnMsg);
    }
}
