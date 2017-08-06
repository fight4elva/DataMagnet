package com.tangxiaotang.dataMagnet.util;

import com.google.common.collect.Lists;
import com.tangxiaotang.dataMagnet.model.QiChaChaInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

/**
 * Created by SixGod on 2017/7/28.
 * 最终实体数据转换
 * 业界推荐使用JSOUP
 */
public class QCCParse {

    public static List<QiChaChaInfo> getData (String html) throws Exception{
        //获取的数据，存放在集合中
        List<QiChaChaInfo> data = Lists.newArrayList();
        //采用Jsoup解析
        Document doc = Jsoup.parse(html);
        //获取html标签中的内容
        Elements elements=doc.select("ul[class=gl-warp clearfix]").select("li[class=gl-item]");
        for (Element ele:elements) {
            String companyName=ele.attr("data-sku");
            String contactEmail=ele.attr("data-sku");
            String contactTel=ele.attr("data-sku");
            String registerTime=ele.attr("data-sku");
            String registerMoney=ele.attr("data-sku");
            String contactName=ele.select("div[class=p-price]").select("strong").select("i").text();
            //创建一个对象，这里可以看出，使用Model的优势，直接进行封装
            QiChaChaInfo qiChaChaInfo=new QiChaChaInfo();
            //对象的值
            qiChaChaInfo.setCompanyName(companyName);
            qiChaChaInfo.setContactEmail(contactEmail);
            qiChaChaInfo.setContactName(contactName);
            qiChaChaInfo.setContactTel(contactTel);
            qiChaChaInfo.setRegisterMoney(registerMoney);
            qiChaChaInfo.setRegisterTime(registerTime);
            //将每一个对象的值，保存到List集合中
            data.add(qiChaChaInfo);
        }
        //返回数据
        return data;
    }
}
