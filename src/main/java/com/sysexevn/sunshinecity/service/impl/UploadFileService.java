package com.sysexevn.sunshinecity.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import com.sysexevn.sunshinecity.service.IUploadFileService;

@Service
public class UploadFileService implements IUploadFileService {

	private final String rootPath = "src\\main\\resources\\static";
	private final Path storageFolder = Paths.get(rootPath);

	public UploadFileService() {
		try {
			Files.createDirectories(storageFolder);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// check file is excel
	private boolean isExcelFile(MultipartFile file) {
		String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
		return Arrays.asList(new String[] { "xlsx", "xls", "csv" }).contains(fileExtension.trim().toLowerCase());
	}

	@Override
	public String storeFile(MultipartFile file, String folderContent) {
		try {
			Path pathUploadFolder = Files.createDirectories(Paths.get(rootPath + "\\" + folderContent));
			// check empty
			if (file.isEmpty())
				throw new RuntimeException();
			// check file
			if (!isExcelFile(file))
				throw new RuntimeException();
			// check size
//			float fileSizeInMegabytes = file.getSize() / 1_000_000.0f;
//			if (fileSizeInMegabytes > 5.0f)
//				throw new RuntimeException("Dung luong file <= 5Mb");
			String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
			String generatedFileName = UUID.randomUUID().toString().replace("-", "");
			generatedFileName = generatedFileName + "." + fileExtension;
			Path destinationFilePath = pathUploadFolder.resolve(Paths.get(generatedFileName)).normalize()
					.toAbsolutePath();
			if (!destinationFilePath.getParent().equals(pathUploadFolder.toAbsolutePath()))
				throw new RuntimeException();
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
			}
			return "/" + folderContent + "/" + generatedFileName;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Stream<Path> loadAll() {
		try {
			// load all file in storageFolder
			return Files.walk(this.storageFolder, 1).filter(path -> !path.equals(this.storageFolder))
					.map(this.storageFolder::relativize);
		} catch (IOException e) {
			throw new RuntimeException("Failed to read stored files", e);
		}
	}

	@Override
	public byte[] readFileContent(String fileName) {
		try {
			Path file = storageFolder.resolve(fileName);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
				return bytes;
			} else {
				throw new RuntimeException("Khong the doc file " + fileName);
			}
		} catch (IOException e) {
			throw new RuntimeException("Khong the doc file " + fileName);
		}
	}

	@Override
	public void deleteAllFiles() {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteFile(String path) {

		String prePath = "src/main/resources/static";

		try {
			FileUtils.touch(new File(prePath + path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		File fileToDelete = FileUtils.getFile(prePath + path);
		FileUtils.deleteQuietly(fileToDelete);
	}
}