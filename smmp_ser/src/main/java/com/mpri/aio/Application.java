package com.mpri.aio;
import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 *.主函数
 * @author Cary
 * @date 2018年7月18日
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching// 开启缓存，需要显示的指定
@EnableAsync// 开启异步
//@EnableScheduling //开启定时任务
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	 @Bean(name = "threadPoolTaskExecutor")
	 public Executor threadPoolTaskExecutor() {
	  return new ThreadPoolTaskExecutor();
	 }
}
