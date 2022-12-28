package com.mycompany.webapp.test.service;

import java.util.List;

import com.mycompany.webapp.test.vo.TestVO;

public interface ITestService {
	List<TestVO> getAllPosts();
	TestVO getPost(int postno);
	void createPost(TestVO post);
	void updatePost(TestVO post);
	void deletePost(int postno);
}
