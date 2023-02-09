package com.mycompany.webapp.notice.service;

import java.util.List;

import com.mycompany.webapp.notice.vo.PostVO;

public interface INoticeService {
	int countAllPosts();
	List<PostVO> getPosts(int pageNo);
	PostVO getPost(int postno);
	void createPost(PostVO post);
	void updatePost(PostVO post);
	void deletePost(int postno);
}
