package server.OUTBREAK_1.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages= "server.OUTBREAK_1")//添加扫包@ComponentScan(basePackages= "")
@EnableAutoConfiguration
public class App{

	/*
	*名称：springboot入口函数
	*描述：启动springboot
	*参数：void
	*返回类型：void
	*作者：周于楷
	*/
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}