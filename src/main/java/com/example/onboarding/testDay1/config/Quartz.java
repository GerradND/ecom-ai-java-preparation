package com.example.onboarding.testDay1.config;

import com.example.onboarding.testDay1.job.JobInsertDataService;
import com.example.onboarding.testDay1.job.MyJobService;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Quartz {
    @Bean
    public JobDetail jobAutoInsertDetails() {
        return JobBuilder.newJob(JobInsertDataService.class)
                .withIdentity("jobInsertData", "test-insert")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger jobAutoInsertTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(jobAutoInsertDetails())
                .withIdentity("trigger-test-insert", "test-insert")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)  // Set the interval
                        .repeatForever())  // Repeat indefinitely
                .build();
    }

    @Bean
    public JobDetail myJobDetail() {
        return JobBuilder.newJob(MyJobService.class)
                .withIdentity("myJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger myJobTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(myJobDetail())
                .withIdentity("myJobTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever())
                .build();
    }
}
