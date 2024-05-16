package com.numetry.service;

import org.springframework.web.multipart.MultipartFile;

import com.numetry.entity.UploadedFile;

public interface FilesService {

	    //files
		public void uploadToLocal(MultipartFile file);
	    public UploadedFile uploadToDb(MultipartFile file);
	    public UploadedFile downloadFile(String fileId);
}