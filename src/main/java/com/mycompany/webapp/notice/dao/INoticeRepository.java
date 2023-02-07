package com.mycompany.webapp.notice.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mycompany.webapp.common.vo.Pager;
import com.mycompany.webapp.notice.vo.PostVO;

@Repository
public interface INoticeRepository {
	int countAllPosts();
	List<PostVO> getPosts(Pager pager);
	PostVO getPost(int postno);
	void createPost(PostVO post);
	void updatePost(PostVO post);
	void deletePost(int postno);
}
