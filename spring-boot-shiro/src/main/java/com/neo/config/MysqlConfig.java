package com.neo.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;

/**
 * @Author shangXoL
 * @Date 2018/11/12 19:28
 **/
public class MysqlConfig extends MySQL5InnoDBDialect {
        @Override
    public String getTableTypeString() {
        return " ENGINE=InnoDB DEFAULT CHARSET=utf8";
    }

}
