package com.neo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "mysql.datasource.test1")  
@Component
public class Db1Config {  
  
    private String url;  
    private String username;  
    private String password;  
    private int minPoolSize;  
    private int maxPoolSize;  
    private int maxLifetime;  
    private int borrowConnectionTimeout;  
    private int loginTimeout;  
    private int maintenanceInterval;  
    private int maxIdleTime;  
    private String testQuery;  
    public String getUrl() {  
        return url;  
    }  
    public void setUrl(String url) {  
        this.url = url;  
    }  
    public String getUsername() {  
        return username;  
    }  
    public void setUsername(String username) {  
        this.username = username;  
    }  
    public String getPassword() {  
        return password;  
    }  
    public void setPassword(String password) {  
        this.password = password;  
    }  
    public int getMinPoolSize() {  
        return minPoolSize;  
    }  
    public void setMinPoolSize(int minPoolSize) {  
        this.minPoolSize = minPoolSize;  
    }  
    public int getMaxPoolSize() {  
        return maxPoolSize;  
    }  
    public void setMaxPoolSize(int maxPoolSize) {  
        this.maxPoolSize = maxPoolSize;  
    }  
    public int getMaxLifetime() {  
        return maxLifetime;  
    }  
    public void setMaxLifetime(int maxLifetime) {  
        this.maxLifetime = maxLifetime;  
    }  
    public int getBorrowConnectionTimeout() {  
        return borrowConnectionTimeout;  
    }  
    public void setBorrowConnectionTimeout(int borrowConnectionTimeout) {  
        this.borrowConnectionTimeout = borrowConnectionTimeout;  
    }  
    public int getLoginTimeout() {  
        return loginTimeout;  
    }  
    public void setLoginTimeout(int loginTimeout) {  
        this.loginTimeout = loginTimeout;  
    }  
    public int getMaintenanceInterval() {  
        return maintenanceInterval;  
    }  
    public void setMaintenanceInterval(int maintenanceInterval) {  
        this.maintenanceInterval = maintenanceInterval;  
    }  
    public int getMaxIdleTime() {  
        return maxIdleTime;  
    }  
    public void setMaxIdleTime(int maxIdleTime) {  
        this.maxIdleTime = maxIdleTime;  
    }  
    public String getTestQuery() {  
        return testQuery;  
    }  
    public void setTestQuery(String testQuery) {  
        this.testQuery = testQuery;  
    }   
}  
