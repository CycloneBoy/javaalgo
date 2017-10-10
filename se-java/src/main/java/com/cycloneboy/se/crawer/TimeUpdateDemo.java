package com.cycloneboy.se.crawer;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by CycloneBoy on 2017/10/10.
 */
public class TimeUpdateDemo {

    public static void go() throws Exception{
        SchedulerFactory sf = new StdSchedulerFactory();

        Scheduler scheduler = sf.getScheduler();



    }
}
