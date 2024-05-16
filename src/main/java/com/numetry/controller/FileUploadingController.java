package com.numetry.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.numetry.entity.TestSeries;
import com.numetry.entity.UploadedFile;
import com.numetry.response.FileUploadResponse;
import com.numetry.service.FilesService;
import com.numetry.service.TestSeriesUploadService;

@RestController
@RequestMapping("/api/v1/auth/school")
public class FileUploadingController {

	@Autowired
	private FilesService service;
	
	@Autowired
	private TestSeriesUploadService service2;
	
	@PostMapping("/upload/local")
	public ResponseEntity<String> uploadLocal(@RequestParam("file") MultipartFile multipartFile) throws IOException {
		 service.uploadToLocal(multipartFile);
		 return new ResponseEntity<>("File Uploaded Successfully",HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/allTest")
	public List Tests() {
		return service2.allTests();
	}

	//Upload Normal/General Files
	@PostMapping("/upload/db")
	public FileUploadResponse uploadDb(@RequestParam("file") MultipartFile multipartFile) {
		UploadedFile uploadedFile = service.uploadToDb(multipartFile);
		FileUploadResponse response = new FileUploadResponse();
		if (uploadedFile != null) {
			String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/auth/school/download/")
					.path(uploadedFile.getFileId()).toUriString();
			response.setDownloadUri(downloadUri);
			response.setFileId(uploadedFile.getFileId());
			response.setFileType(uploadedFile.getFileType());
			response.setUploadStatus(true);
			response.setMessage("File Uploaded Successfully!");
			return response;

		}
		response.setMessage("Oops 1 something went wrong please re-upload.");
		return response;
	}
	
	//Uploading Test Series
	@PostMapping("/uploadTestSeries/db")
	public FileUploadResponse uploadTestSeries(@RequestParam("file") MultipartFile multipartFile) {
		TestSeries uploadedFile = service2.uploadToDb(multipartFile);
		FileUploadResponse response = new FileUploadResponse();
		if (uploadedFile != null) {
			String downloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/auth/school/downloadTestSeries/")
					.path(uploadedFile.getFileId()).toUriString();
			response.setDownloadUri(downloadUri);
			response.setFileId(uploadedFile.getFileId());
			response.setFileType(uploadedFile.getFileType());
			response.setUploadStatus(true);
			response.setMessage("File Uploaded Successfully!");
			return response;
			
		}
		response.setMessage("Oops 1 something went wrong please re-upload.");
		return response;
	}

	//Download Normal/General Files
	@GetMapping("/download/{id}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String id) {
		UploadedFile uploadedFileToRet = service.downloadFile(id);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(uploadedFileToRet.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= " + uploadedFileToRet.getFileName())
				.body(new ByteArrayResource(uploadedFileToRet.getFileData()));
	}
	
	//Download Test Series
	@GetMapping("/downloadTestSeries/{id}")
	public ResponseEntity<Resource> downloadTestSeries(@PathVariable String id) {
		TestSeries uploadedFileToRet = service2.downloadFile(id);
		return ResponseEntity.ok().contentType(MediaType.parseMediaType(uploadedFileToRet.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= " + uploadedFileToRet.getFileName())
				.body(new ByteArrayResource(uploadedFileToRet.getFileData()));
	}
}
