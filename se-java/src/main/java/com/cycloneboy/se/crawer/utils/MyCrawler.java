package com.cycloneboy.se.crawer.utils;

import javafx.scene.media.VideoTrack;

/**
 * Created by CycloneBoy on 2017/10/11.
 */
public class MyCrawler {

    //使用种子初始化URL队列
    private void initCrawlerWithSeeds(String[] seeds){
        for(int i = 0; i < seeds.length;i++){
            LinkQueue.addUnvisitedUrl(seeds[i]);
        }
    }

    //抓取过程
    public void crawling(String[] seeds,String homeUrl){
        LinkFilter filter = new LinkFilter() {
            @Override
            public boolean accept(String url) {
                if(url.startsWith(homeUrl)){
                    return true;
                }else {
                    return false;
                }
            }
        };

        initCrawlerWithSeeds(seeds);

        while(!LinkQueue.unVisitedUrlEmpty()
                && LinkQueue.getVisitedUrlNum() <=1000){
            //队头URL出队列
            String visitUrl = (String)LinkQueue.unVisitedUrlDeQueue();
            if(visitUrl == null){
                continue;
            }
            //下载网页
            DownLoadFile downLoadFile = new DownLoadFile();
            downLoadFile.downloadFile(visitUrl);

            LinkQueue.addVisitedUrl(visitUrl);

        }
    }
}
