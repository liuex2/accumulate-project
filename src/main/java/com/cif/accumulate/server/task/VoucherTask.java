package com.cif.accumulate.server.task;

/**
 * @Description 心意券定时器任务
 * @Author liuxincai
 * @create 2018-11-24-17:47
 **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Description 心意券定时任务
 * @Author liuxincai
 * @create 2018-11-24-17:04
 **/
@Configuration          //证明这个类是一个配置文件
@EnableScheduling       //打开quartz定时器总开关
public class VoucherTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 每天3:00触发一次定时器任务
     */
//    @Scheduled(cron = "0 0 3 * * ?")
//    @Scheduled(cron = "0/2 * * * * *")
    public void cycleMethod() {
        //获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        logger.info("当前时间为:" + localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

}