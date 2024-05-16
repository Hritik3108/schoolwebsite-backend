package com.numetry.service;

import java.util.List;

import com.numetry.entity.Notice;

public interface HeadLineService {
	
	public Notice createNotice(Notice notice);
	public String deleteNotice(String noticeId);
	public String updateNotice(String noticeId,Notice notice);
	public List<Notice> getAllNotice();

}
