package com.tangxiaotang.dataMagnet.db;

import com.tangxiaotang.dataMagnet.util.ResourceUtil;
import org.apache.commons.lang.StringUtils;

import java.util.Properties;

public class Config {

    private String dataUrl;
    private String dbName;
    private String tableName;
    private String ip;
    private String port;
    private String userName;
    private String password;

    private String grabUrl;


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getGrabUrl() {
        return grabUrl;
    }

    public void setGrabUrl(String grabUrl) {
        this.grabUrl = grabUrl;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static Config init() throws Exception {
        //获取database文件的属性设置 完成 上下文初始化操作
        Properties properties = ResourceUtil.loadPropertiesFromClassPath("application.properties");
        String ip = properties.getProperty("ip");
        String port = properties.getProperty("port");
        String userName = properties.getProperty("userName");
        String password = properties.getProperty("password");
        String dbName = properties.getProperty("dbName");
        String tableName = properties.getProperty("tableName");
        String grabUrl = properties.getProperty("grabUrl");

        if (StringUtils.isBlank(ip)) {
            throw new Exception("ip不能为空");
        }
        if (StringUtils.isBlank(port)) {
            port = "3306";
        }
        if (StringUtils.isBlank(userName)) {
            throw new Exception("userName不能为空");
        }
        if (StringUtils.isBlank(password)) {
            throw new Exception("password不能为空");
        }
        if (StringUtils.isBlank(dbName)) {
            throw new Exception("dbName不能为空");
        }
        if (StringUtils.isBlank(grabUrl)) {
            throw new Exception("grabUrl不能为空");
        }
        if (StringUtils.isBlank(tableName)) {
            throw new Exception("table不能为空");
        }

        Config cfg = new Config();
        cfg.setDbName(dbName);
        cfg.setTableName(tableName);
        cfg.setIp(ip);
        cfg.setPassword(password);
        cfg.setPort(port);
        cfg.setUserName(userName);
        cfg.setDataUrl("jdbc:mysql://" + ip + ":" + port + "/" + dbName);
        cfg.setGrabUrl(grabUrl);

        return cfg;
    }
}