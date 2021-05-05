package com.kandao.apiapplication.utils;


import lombok.experimental.UtilityClass;

import java.util.logging.Logger;

@UtilityClass
public class LoggerUtils {
    private static final Logger logger = Logger.getLogger(LoggerUtils.class.getName());

    public static Logger getLogger(){
        return logger;
    }
}
