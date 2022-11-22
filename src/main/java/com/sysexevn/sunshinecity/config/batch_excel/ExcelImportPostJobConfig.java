package com.sysexevn.sunshinecity.config.batch_excel;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sysexevn.sunshinecity.dto.PostDTO;
import com.sysexevn.sunshinecity.entity.Post;

@Configuration
@EnableBatchProcessing
public class ExcelImportPostJobConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job importPostJob(Step importPostStep) {
		return jobBuilderFactory.get("importPostJob").incrementer(new RunIdIncrementer())
				.listener(new JobCompletionNotificationListener()).start(importPostStep).build();
	}

	@Bean
	public Step importPostStep(ExcelImportPostReader reader, ExcelImportPostProcessor processor, ExcelImportPostWriter writer) {
		return stepBuilderFactory.get("importPostStep").<PostDTO, Post>chunk(100).reader(reader).processor(processor)
				.writer(writer).build();
	}
}
