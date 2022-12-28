package com.mycompany.webapp.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycompany.webapp.test.dao.ITestRepository;
import com.mycompany.webapp.test.vo.TestVO;

@Service
public class TestService implements ITestService {

	@Autowired
	ITestRepository testRepository;
	
	@Override
	public List<TestVO> getAllPosts() {
		return testRepository.getAllPosts();
	}
	
	@Override
	public TestVO getPost(int postno) {
		return testRepository.getPost(postno);
	}

	@Override
	public void createPost(TestVO post) {
		testRepository.createPost(post);
	}
	
	@Override
	public void updatePost(TestVO post) {
		testRepository.updatePost(post);
	}
	
	@Override
	public void deletePost(int postno) {
		testRepository.deletePost(postno);	
	}
}
