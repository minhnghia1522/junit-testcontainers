package com.sysexevn.sunshinecity.service;

import java.nio.file.Path;
import java.util.stream.Stream;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadFileService {

	public String storeFile(MultipartFile file, String folderContent);
	public Stream<Path> loadAll();
	public byte[] readFileContent(String fileName);
	public void deleteAllFiles();
	public void deleteFile(String path);
}
