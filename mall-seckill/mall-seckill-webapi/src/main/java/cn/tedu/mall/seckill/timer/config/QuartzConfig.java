package cn.tedu.mall.seckill.timer.config;

import cn.tedu.mall.seckill.timer.job.SeckillBloomJob;
import cn.tedu.mall.seckill.timer.job.SeckillInitialJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail initJobDetail(){
        return JobBuilder.newJob(SeckillInitialJob.class)
                .withIdentity("initSeckill")
                .storeDurably()
                .build();
    }
    @Bean
    public Trigger initTrigger(){
        // 为了方便学习过程中的测试和运行的观察,cron表达式设置每分钟触发一次
        CronScheduleBuilder cron=
                CronScheduleBuilder.cronSchedule("0 0/1 * * * ?");
        return TriggerBuilder.newTrigger()
                .forJob(initJobDetail())
                .withIdentity("initTrigger")
                .withSchedule(cron)
                .build();
    }
    @Bean
    public JobDetail bloomJobDetail(){
        return JobBuilder.newJob(SeckillBloomJob.class)
                .withIdentity("bloomJobDetial")
                .build();
    }
    @Bean
    public Trigger bloomTrigger(){
        CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule("0 0/1 * * * ?");
        return TriggerBuilder.newTrigger()
                .withSchedule(cron)
                .forJob(initJobDetail())
                .withIdentity("bloomTrigger")
                .withSchedule(cron)
                .build();
    }

    @Bean
    public JobDetail bloomJobDetail(){
        return JobBuilder.newJob(SeckillBloomJob.class)
                .withIdentity("bloomJobDetail")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger bloomTrigger(){
        CronScheduleBuilder cron=
                CronScheduleBuilder.cronSchedule("0 0/1 * * * ?");
        return TriggerBuilder.newTrigger()
                .withSchedule(cron)
                .forJob(bloomJobDetail())
                .withIdentity("bloomTrigger")
                .build();
    }



}
