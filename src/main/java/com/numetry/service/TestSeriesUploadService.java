package com.numetry.service;

import org.springframework.web.multipart.MultipartFile;

import com.numetry.entity.TestSeries;
import java.util.List;

public interface TestSeriesUploadService {

	public void uploadToLocal(MultipartFile file);
	public TestSeries uploadToDb(MultipartFile file);
    public TestSeries downloadFile(String fileId);
	public List<TestSeries> allTests();
}
