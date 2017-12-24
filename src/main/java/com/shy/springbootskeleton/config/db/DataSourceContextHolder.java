package com.shy.springbootskeleton.config.db;

import com.shy.springbootskeleton.config.enumeration.db.DataSourceType;

/**
 * 当前线程的数据源holder
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<String> local = new ThreadLocal<>();

    public static ThreadLocal<String> getLocal(){
        return local;
    }

    public static void read(){
        local.set(DataSourceType.read.getType());
    }

    public static void write(){
        local.set(DataSourceType.write.getType());
    }

    public static String getJdbcType(){
        return local.get();
    }
}
