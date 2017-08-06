package com.tangxiaotang.dataMagnet.db;

import com.tangxiaotang.dataMagnet.model.QiChaChaInfo;
import org.apache.commons.lang.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DataBase {
    private Config cfg = null;

    public DataBase(Config cfg) {
        this.cfg = cfg;
    }

    public Config getCfg() {
        return cfg;
    }


    public void saveInfo(List<QiChaChaInfo> qiChaChaInfos) {

        Statement stm = null;
        Connection conn = null;

        try {
            conn = connect();
            if (conn != null) {
                stm = conn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
                for (QiChaChaInfo qiChaChaInfo : qiChaChaInfos) {
                    String sql = buildSql(qiChaChaInfo);
                    //执行插入
                    int columNums = stm.executeUpdate(sql);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (stm != null) {
                    stm.close();
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    private String buildSql(QiChaChaInfo qiChaChaInfo) {

        String sql = "insert into jingdongbook (company_name, contact_name, contact_email,contact_tel,register_time,register_money) values ("+
                qiChaChaInfo.getCompanyName()+ ","+
                qiChaChaInfo.getContactName()+ ","+
                qiChaChaInfo.getContactEmail()+ ","+
                qiChaChaInfo.getContactTel()+ ","+
                qiChaChaInfo.getRegisterTime()+ ","+
                qiChaChaInfo.getRegisterMoney()+ ")";
        return sql;
    }

    public Connection connect() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(cfg.getDataUrl(),
                    cfg.getUserName(), cfg.getPassword());

        } catch (Exception e) {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
        return conn;
    }

}
