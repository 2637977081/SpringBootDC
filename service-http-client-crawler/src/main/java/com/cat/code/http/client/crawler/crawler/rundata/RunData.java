package com.cat.code.http.client.crawler.crawler.rundata;

/**
 * @Author: lvgang
 * @Time: 2019/11/12 14:49
 * @Email: lvgang@golaxy.cn
 * @Description: todo
 */
public abstract class RunData {

    public RunData() {
    }

    public abstract boolean addUrl(String var1);

    public abstract String getUrl();

    public abstract boolean getUrlExist();
}
