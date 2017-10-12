package com.cycloneboy.se.crawer.utils;

/**
 * Created by CycloneBoy on 2017/10/11.
 */
public interface LinkFilter {
    public boolean accept(String url);
}
