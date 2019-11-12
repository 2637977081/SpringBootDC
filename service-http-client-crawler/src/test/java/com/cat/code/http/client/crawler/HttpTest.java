package com.cat.code.http.client.crawler;

import com.cat.code.http.client.crawler.service.HttpClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: lvgang
 * @Time: 2019/11/12 13:57
 * @Email: lvgang@golaxy.cn
 * @Description: todo
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceHttpClientCrawlerApplication.class)
public class HttpTest {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpClientService httpClientService;

    @Test
    public void test1(){
        ExecutorService executor = Executors.newFixedThreadPool(100);
        for (int i=0;i<100;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        ResponseEntity<String> responseEntity =  restTemplate.getForEntity("https://www.dapp.review/api/dapp/dapp/11649/?dapp=11649&lang=zh",String.class);
                        System.out.println(responseEntity.getBody());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            });
        }
        //等待线程执行完毕
        executor.shutdown();
        while(!executor.isTerminated()) {
        }
    }

    @Test
    public void test2(){
        httpClientService.test();
    }

}
