package com.mycompany.webapp.test.vo;

import java.sql.Date;

public class TestVO {
	private int postno;
	private String title;
	private String content;
	private String author;
	private Date posttime;
	
	//생성자 
	public TestVO() {
	}

	//getter,setter
	public int getPostNo() {
		return postno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPosttime() {
		return posttime;
	}

	public void setPosttime(Date posttime) {
		this.posttime = posttime;
	}

	//toString
	@Override
	public String toString() {
		return "TestVO [postNo=" + postno + ", title=" + title + ", content=" + content + ", author=" + author
				+ ", posttime=" + posttime + "]";
	}
}
