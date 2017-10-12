package com.cycloneboy.se.crawer.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by CycloneBoy on 2017/10/11.
 */
public class LinkQueue {
    private static Set visitedUrl = new HashSet();
    private static Queue unVisitedUrl = new Queue();

    public static Queue getUnVisitedUrl(){
        return  unVisitedUrl;
    }

    public  static void addVisitedUrl(String url){
        visitedUrl.add(url);
    }

    public static void removeVisitedUrl(String url){
        visitedUrl.remove(url);
    }

    public static Object unVisitedUrlDeQueue(){
        return unVisitedUrl.deQueue();
    }

    public static void addUnvisitedUrl(String url){
        if(url != null && !url.trim().equals("")
                && visitedUrl.contains(url) && !unVisitedUrl.contains(url)){
            unVisitedUrl.enQueue(url);
        }
    }

    public static int getVisitedUrlNum(){
        return  visitedUrl.size();
    }

    public static boolean unVisitedUrlEmpty(){
        return unVisitedUrl.empty();
    }
}
