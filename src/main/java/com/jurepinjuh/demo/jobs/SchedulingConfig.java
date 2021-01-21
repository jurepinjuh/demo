package com.jurepinjuh.demo.jobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulingConfig {

    @Bean
    public JobDetail totalPurchaseTodayJobDetail(){
        return JobBuilder.newJob(TotalPurchasedJob.class).withIdentity("totalPurchaseJob").storeDurably().build();
    }

    @Bean
    public JobDetail articlesInEmailJobDetail(){
        return JobBuilder.newJob(SendArticlesEmailJob.class).withIdentity("emailJob").storeDurably().build();
    }

    @Bean
    public Trigger totalPurchaseTodayTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(60).repeatForever();

        return TriggerBuilder.newTrigger().forJob(totalPurchaseTodayJobDetail())
                .withIdentity("totalPurchaseJob").withSchedule(scheduleBuilder).build();
    }

    @Bean
    public Trigger articlesInEmailTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInHours(24).repeatForever();

        return TriggerBuilder.newTrigger().forJob(articlesInEmailJobDetail())
                .withIdentity("emailJob").withSchedule(scheduleBuilder).build();
    }
}
