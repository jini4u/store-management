package com.mycompany.webapp.test.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mycompany.webapp.test.vo.TestVO;

@Repository
public interface ITestRepository {
	public int test();
	List<TestVO> getAllPosts();
}
