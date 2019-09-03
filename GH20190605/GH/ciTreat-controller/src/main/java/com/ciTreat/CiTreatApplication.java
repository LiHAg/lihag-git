package com.ciTreat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动程序
 * 
 * @author jinma.zheng
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
//@SpringBootApplication()
@MapperScan({"com.ciTreat.*.mapper","com.golden.*.mapper","com.golden.*.*.mapper"})
@EntityScan({"com.golden.*","com.ciTreat.*"})
@ComponentScan({"com.golden","com.ciTreat","com.dhm"})
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CiTreatApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
    	ApplicationContext applicationContext = SpringApplication.run(CiTreatApplication.class, args);
    	//applicationContext.addListeners(new ListenerStartup());
        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }
 
        
        System.out.println(
        		" ******************** ********************\n" +
                " ******************** ********************\n" +
                " ******************** ********************\n" +
                " **************  启动成功  ***************ﾞ \n" +
                " ******************** ********************\n" +
                " ******************** ********************\n" +
                " ******************** ********************\n" +
                " ******************** ********************\n" );
    }
}