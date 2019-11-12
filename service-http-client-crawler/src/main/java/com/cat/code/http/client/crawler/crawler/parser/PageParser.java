package com.cat.code.http.client.crawler.crawler.parser;


/**
 * @Author: lvgang
 * @Time: 2019/11/12 17:33
 * @Email: lvgang@golaxy.cn
 * @Description: todo
 */
public abstract class PageParser<T> {
    public PageParser() {
    }

    public abstract void parse(String url,String body);
}
