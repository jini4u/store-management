package com.mycompany.webapp.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.common.vo.Pager;
import com.mycompany.webapp.notice.dao.INoticeRepository;
import com.mycompany.webapp.notice.vo.PostVO;

@Service
public class NoticeService implements INoticeService {

	@Autowired
	INoticeRepository noticeRepository;
	
	@Override
	public int countAllPosts() {
		return noticeRepository.countAllPosts();
	}
	
	@Override
	public List<PostVO> getPosts(int pageNo) {
		Pager pager = new Pager(10, 10, noticeRepository.countAllPosts(), pageNo);
		return noticeRepository.getPosts(pager);
	}
	
	@Override
	public PostVO getPost(int postno) {
		return noticeRepository.getPost(postno);
	}

	@Override
	public void createPost(PostVO post) {
		noticeRepository.createPost(post);
	}
	
	@Override
	public void updatePost(PostVO post) {
		noticeRepository.updatePost(post);
	}
	
	@Override
	public void deletePost(int postno) {
		noticeRepository.deletePost(postno);	
	}
}
