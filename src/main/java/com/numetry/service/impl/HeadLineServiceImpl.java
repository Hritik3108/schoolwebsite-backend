package com.numetry.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.numetry.entity.Notice;
import com.numetry.repository.HeadLineRepo;
import com.numetry.service.HeadLineService;

@Service
public class HeadLineServiceImpl implements HeadLineService {

	@Autowired
	private HeadLineRepo repo;
	
	@Override
	public Notice createNotice(Notice notice) {
		// TODO Auto-generated method stub
		return repo.save(notice);
	}

	@Override
	public String deleteNotice(String noticeId) {
		// TODO Auto-generated method stub
		repo.deleteById(noticeId);
		return "notice with notice Id "+noticeId+" is deleted";
	}

	@Override
	public String updateNotice(String noticeId, Notice notice) {
		// TODO Auto-generated method stub
		notice.setNoticeId(noticeId);
		repo.save(notice);
		return "Notice with notice Id "+noticeId+" is updated";
	}

	@Override
	public List<Notice> getAllNotice() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

}
