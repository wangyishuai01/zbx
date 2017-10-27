package com.cn.zbx.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 基于注解的定时器
 * @author zyw
 */
@Component
public class TimerTask {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TimerTask.class);
	
	/** 
     * 定时计算。每天凌晨 01:00 执行一次 
     */  
//  @Scheduled(cron = "0 0 1 * * *") 
//	public void show(){
//		System.out.println("Annotation：is show run");
//	}
	
	
    /** 
     * 心跳更新。启动时执行一次，之后每隔 1000 * 60 * 30 秒执行一次 
     */  
    @Scheduled( fixedRate = 1000 * 60 * 30) 
	public void setWeChatAccessTokenToCache(){
    	
    }
}