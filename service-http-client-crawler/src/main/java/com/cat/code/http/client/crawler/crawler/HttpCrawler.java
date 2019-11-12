package com.cat.code.http.client.crawler.crawler;

import com.cat.code.http.client.crawler.crawler.rundata.RedisRunData;
import com.cat.code.http.client.crawler.crawler.rundata.RunData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lvgang
 * @Time: 2019/11/12 14:48
 * @Email: lvgang@golaxy.cn
 * @Description: todo
 */
public class HttpCrawler {
    private final Logger logger = LoggerFactory.getLogger(HttpCrawler.class);

    private volatile RunData runData = new RedisRunData();
    private volatile CrawlerMetaData crawlerMetaData;

    private int threadCount = 1;
    private ExecutorService crawlers = Executors.newCachedThreadPool();
    private List<CrawlerThread> crawlerThreads = new CopyOnWriteArrayList();

    public HttpCrawler(CrawlerMetaData crawlerMetaData){
        this.crawlerMetaData = crawlerMetaData;
        this.runData = crawlerMetaData.getRunData();
        this.threadCount = crawlerMetaData.getThreadCount();
    }

    public void start(boolean sync) {
        if (this.runData == null) {
            throw new RuntimeException("crawler runData can not be null.");
        } else if (!this.runData.getUrlExist()) {
            throw new RuntimeException("crawler indexUrl can not be empty.");
        }else if (this.threadCount >= 1 && this.threadCount <= 1000) {

            CrawlerThread crawlerThread;
            for(int i = 0; i < this.threadCount; ++i) {
                crawlerThread = new CrawlerThread(crawlerMetaData);
                this.crawlerThreads.add(crawlerThread);
            }
            Iterator var5 = this.crawlerThreads.iterator();
            while(var5.hasNext()) {
                crawlerThread = (CrawlerThread)var5.next();
                this.crawlers.execute(crawlerThread);
            }
            this.crawlers.shutdown();
            if (sync) {
                try {
                    while(!this.crawlers.awaitTermination(5L, TimeUnit.SECONDS)) {
                        logger.info(">>>>>>>>>>> xxl crawler still running ...");
                    }
                } catch (InterruptedException var4) {
                    logger.error(var4.getMessage(), var4);
                }
            }
        } else {
            throw new RuntimeException("xxl crawler threadCount invalid, threadCount : " + this.threadCount);
        }
    }

}
