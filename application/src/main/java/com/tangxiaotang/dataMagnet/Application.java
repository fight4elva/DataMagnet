package com.tangxiaotang.dataMagnet;
import com.alibaba.fastjson.JSON;
import com.tangxiaotang.dataMagnet.db.Config;
import com.tangxiaotang.dataMagnet.db.DataBase;
import com.tangxiaotang.dataMagnet.model.QiChaChaInfo;
import com.tangxiaotang.dataMagnet.util.URLFecter;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by SixGod on 2017/7/28.
 * 抓取入口
 */
public class Application {

    //log4j的是使用，不会的请看之前写的文章
    static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        //初始化上下文
        Config cfg = Config.init();
        DataBase dataBase = new DataBase(cfg);
        //初始化一个httpclient
        HttpClient client = new DefaultHttpClient();
        //抓取的数据
        List<QiChaChaInfo> qiChaChaInfos= URLFecter.URLParser(client, cfg.getGrabUrl());
        //循环输出抓取的数据
        for (QiChaChaInfo qiChaChaInfo : qiChaChaInfos) {
            logger.info("抓取到的房产公司信息为:{}", JSON.toJSON(qiChaChaInfo));
            //将抓取的数据插入数据库
            dataBase.saveInfo(qiChaChaInfo);
        }

    }
}
