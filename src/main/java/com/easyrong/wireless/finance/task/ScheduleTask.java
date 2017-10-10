package com.easyrong.wireless.finance.task;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleTask {
    public static final Logger logger = LogManager.getLogger(ScheduleTask.class.getName());

    @Scheduled(fixedRate = 360000, initialDelay = 1000)
    public void printSay() {
        logger.info("[定时任务] ScheduleTask time = " + new Date());
    }
}
