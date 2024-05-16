package com.numetry.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.numetry.entity.TestSeries;
import com.numetry.repository.TestSeriesUpload;
import com.numetry.service.TestSeriesUploadService;

@Service
public class TestSeriesUploadServiceImpl implements TestSeriesUploadService {

	private String uploadFolderPath = "D:\\Numetry_Product_Data";
	@Autowired
	private TestSeriesUpload testUploadRepo;
	
	@Override
	public List<TestSeries> allTests(){
		List<TestSeries> ls=testUploadRepo.findAll();
		return ls;
	}
	
	@Override
	public void uploadToLocal(MultipartFile file) {

		try {
			byte[] data = file.getBytes();
			Path path = Paths.get(uploadFolderPath + file.getOriginalFilename());
			Files.write(path, data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public TestSeries uploadToDb(MultipartFile file) {

		TestSeries uploadedFile = new TestSeries();
		try {
			uploadedFile.setFileData(file.getBytes());
			uploadedFile.setFileType(file.getContentType());
			uploadedFile.setFileName(file.getOriginalFilename());
			TestSeries uploadedFileToRet = testUploadRepo.save(uploadedFile);
			return uploadedFileToRet;
		} catch(IOException e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public TestSeries downloadFile(String fileId) {
		TestSeries uploadedFileToRet =testUploadRepo.getOne(fileId);
		return uploadedFileToRet;
	}

}
