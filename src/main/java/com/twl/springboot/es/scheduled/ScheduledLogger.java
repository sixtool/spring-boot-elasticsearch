package com.twl.springboot.es.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class ScheduledLogger {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledLogger.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000,initialDelay = 1000)
    public void time1() {
        Date date = new Date();
        logger.info("pushing {}", dateFormat.format(date));
    }

    @Scheduled(fixedRate = 7000,initialDelay = 2000)
    public void time2() {
        Date date = new Date();
        logger.info("push fail {}", dateFormat.format(date));
    }

    @Scheduled(fixedRate = 10000,initialDelay = 4000)
    public void time3() {
        Date date = new Date();
        logger.info("push successed {}", dateFormat.format(date));
    }

}
