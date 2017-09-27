package com.easyrong.wireless.finance.task;

import com.easyrong.wireless.finance.aop.FinanceAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ScheduleTask {
    private Logger logger = LoggerFactory.getLogger(FinanceAspect.class);

    @Scheduled(fixedRate = 5000,initialDelay = 1000)
    public void printSay() {
        logger.info("[Roc] ScheduleTask time = " + new Date());
    }
}
