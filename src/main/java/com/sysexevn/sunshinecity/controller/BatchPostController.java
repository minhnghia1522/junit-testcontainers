package com.sysexevn.sunshinecity.controller;

import java.util.Arrays;

import org.apache.commons.io.FilenameUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sysexevn.sunshinecity.service.IUploadFileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/batch")
@RequiredArgsConstructor
@Slf4j
public class BatchPostController {

	@Autowired
	private IUploadFileService uploadFileService;

	private final JobLauncher jobLauncher;

	@Qualifier("jobCSV")
	private final Job jobCSV;

	@Qualifier("jobExcel")
	private final Job jobExcel;

	@PostMapping("/upload")
	public ResponseEntity<String> importCsvToDBJob(@RequestParam(value = "file", required = false) MultipartFile file) {

		try {
			String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
			String generatedFilename = "";
			if (isFileCSV(fileExtension)) {
				generatedFilename = uploadFileService.storeFile(file, "csv");

				log.info("BatchPostController - importCsvToDBJob is called");

				JobParameters jobParameters = new JobParametersBuilder().addLong("startAt", System.currentTimeMillis())
						.addString("path", generatedFilename).toJobParameters();

				jobLauncher.run(jobCSV, jobParameters);
			} else if (isFileExcel(fileExtension)) {
				generatedFilename = uploadFileService.storeFile(file, "excel");

				log.info("BatchPostController - importExcelToDBJob is called");

				JobParameters jobParameters = new JobParametersBuilder().addLong("startAt", System.currentTimeMillis())
						.addString("path", generatedFilename).toJobParameters();

				jobLauncher.run(jobExcel, jobParameters);
			}
			// delete file
			uploadFileService.deleteFile(generatedFilename);

		} catch (Exception e) {
			log.info("BatchController | importCsvToDBJob | error : " + e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<>("Batch process for upload fail!!", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Batch process for upload complete!!", HttpStatus.OK);
	}

	boolean isFileCSV(String fileExtension) {
		if (Arrays.asList(new String[] { "csv" }).contains(fileExtension.trim().toLowerCase())) {
			return true;
		}
		return false;
	}

	boolean isFileExcel(String fileExtension) {
		if (Arrays.asList(new String[] { "xlsx", "xls" }).contains(fileExtension.trim().toLowerCase())) {
			return true;
		}
		return false;
	}
}
