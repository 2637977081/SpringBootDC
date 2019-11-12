package com.cat.code.http.client.crawler.crawler;

import com.cat.code.http.client.crawler.crawler.parser.PageParser;
import com.cat.code.http.client.crawler.crawler.rundata.RunData;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author: lvgang
 * @Time: 2019/11/12 15:15
 * @Email: lvgang@golaxy.cn
 * @Description: 爬取参数
 */
public class CrawlerMetaData {

    private RunData runData;

    private RestTemplate restTemplate;
    
    private Map<String,String> headerMap;

    private Map<String,Object> paramMap;

    private PageParser pageParser;

    private boolean isPost;
    
    private int pauseMillis;
    
    private int threadCount;

    public static class Builder{

        private CrawlerMetaData crawler = new CrawlerMetaData();

        public CrawlerMetaData.Builder setRunData(RunData runData){
            this.crawler.runData = runData;
            return this;
        }

        public CrawlerMetaData.Builder setUrls(String... urls) {
            if (urls != null && urls.length > 0) {
                String[] var2 = urls;
                int var3 = urls.length;

                for(int var4 = 0; var4 < var3; ++var4) {
                    String url = var2[var4];
                    this.crawler.runData.addUrl(url);
                }
            }

            return this;
        }

        public CrawlerMetaData.Builder setRestTemplate(RestTemplate restTemplate){
            this.crawler.restTemplate = restTemplate;
            return this;
        }

        public CrawlerMetaData.Builder setPageParser(PageParser pageParser) {
            this.crawler.setPageParser(pageParser);
            return this;
        }

        public CrawlerMetaData.Builder setHeaderMap(Map<String, String> headerMap) {
            this.crawler.headerMap=headerMap;
            return this;
        }

        public CrawlerMetaData.Builder setParamMap(Map<String, Object> paramMap) {
            this.crawler.paramMap=paramMap;
            return this;
        }

        public CrawlerMetaData.Builder setIsPost(boolean isPost) {
            this.crawler.isPost = isPost;
            return this;
        }

        public CrawlerMetaData.Builder setPauseMillis(int pauseMillis) {
            this.crawler.pauseMillis = pauseMillis;
            return this;
        }

        public CrawlerMetaData.Builder setThreadCount(int threadCount) {
            this.crawler.threadCount = threadCount;
            return this;
        }

        public CrawlerMetaData build(){
            return this.crawler;
        }

    }

    public RunData getRunData() {
        return runData;
    }

    public void setRunData(RunData runData) {
        this.runData = runData;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }

    public boolean isPost() {
        return isPost;
    }

    public void setPost(boolean post) {
        isPost = post;
    }


    public int getPauseMillis() {
        return pauseMillis;
    }

    public void setPauseMillis(int pauseMillis) {
        this.pauseMillis = pauseMillis;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public PageParser getPageParser() {
        return pageParser;
    }

    public void setPageParser(PageParser pageParser) {
        this.pageParser = pageParser;
    }
}
