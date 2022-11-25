package com.sysexevn.sunshinecity.config.batch_csv;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sysexevn.sunshinecity.config.batch_excel.JobCompletionNotificationListener;
import com.sysexevn.sunshinecity.dto.PostDTO;
import com.sysexevn.sunshinecity.entity.Post;

@Configuration
@EnableBatchProcessing
public class CsvImportPostJobConfig {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job csvImportPostJob(Step csvImportPostStep) {
		return jobBuilderFactory.get("csvImportPostJob").incrementer(new RunIdIncrementer())
				.listener(new JobCompletionNotificationListener()).start(csvImportPostStep).build();
	}

	@Bean
	public Step csvImportPostStep(CsvImportPostReader reader, CsvImportPostProcessor processor,
			CsvImportPostWriter writer) {
		return stepBuilderFactory.get("csvImportPostStep").<PostDTO, Post>chunk(100).reader(reader).processor(processor)
				.writer(writer).build();
	}
}
