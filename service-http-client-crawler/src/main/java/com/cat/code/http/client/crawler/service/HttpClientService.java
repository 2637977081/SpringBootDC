package com.cat.code.http.client.crawler.service;

import com.cat.code.http.client.crawler.crawler.CrawlerMetaData;
import com.cat.code.http.client.crawler.crawler.HttpCrawler;
import com.cat.code.http.client.crawler.crawler.parser.PageParser;
import com.cat.code.http.client.crawler.crawler.rundata.RedisRunData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: lvgang
 * @Time: 2019/11/12 14:46
 * @Email: lvgang@golaxy.cn
 * @Description: todo
 */
@Service
public class HttpClientService {

    private final Logger logger = LoggerFactory.getLogger(HttpClientService.class);

    @Autowired
    private RedisRunData redisRunData;

    @Autowired
    RestTemplate restTemplate;

    public void test(){

        redisRunData.setTaskQueue("testHttpClient");
        for(int i=1000;i<2000;i++){
            String url = "https://www.dapp.review/api/dapp/dapp/1"+i+"?dapp=11649&lang=zh";
            redisRunData.addUrl(url);
        }

        CrawlerMetaData crawlerMetaData =new CrawlerMetaData.Builder()
                .setRunData(redisRunData)
                .setHeaderMap(null)
                .setIsPost(false)
                .setPauseMillis(2000)
                .setRestTemplate(restTemplate)
                .setThreadCount(5)
                .setPageParser(
                        new PageParser() {
                            @Override
                            public void parse(String url, String body) {
                                logger.info("response code:{},body:{}",url,body);
                            }
                        }
                )
                .build();
        HttpCrawler httpCrawler = new HttpCrawler(crawlerMetaData);
        httpCrawler.start(true);
    }

}
