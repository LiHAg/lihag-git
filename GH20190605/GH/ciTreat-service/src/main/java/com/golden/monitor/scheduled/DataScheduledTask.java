package com.golden.monitor.scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
/**
 * 
 * @author jinma.zheng
 *
 */
@Component
//@Import(value = {RestTemplateConfig.class})
public class DataScheduledTask{
	DataScheduledTask(){
		System.out.println("+++++++++++++++++DataScheduledTask+++++++++++++++++++++=");
	}
	
	@Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

	/**
	 * 折线图、条形图推送
	 * 每1秒执行一次
	 */
	@Scheduled(cron="0/3 * *  * * ? ")
	public void getLineAndBarDataScheduledTask(){
		Random random = new Random();
		Map<String,String> map = new HashMap<String,String>(4);
		//map.put("最低气温",String.valueOf(random.nextInt(10)));
		map.put("Current",String.valueOf(random.nextInt(10)));
		map.put("xxx",LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
		simpMessagingTemplate.convertAndSend("/topic/line/data", JSON.toJSONString(map));
		//simpMessagingTemplate.convertAndSend("/topic/bar/data",JSON.toJSONString(map));
		//System.out.println("我是DataScheduledTask,getLineDataScheduledTask()正在发送后台推送数据："+ JSON.toJSONString(map));
	}



}
