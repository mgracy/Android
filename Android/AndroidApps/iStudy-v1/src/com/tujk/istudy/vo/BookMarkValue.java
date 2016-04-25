/**
 * 
 */
package com.tujk.istudy.vo;

/**
 * title  : BookMarkValue.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-6-4
 */
public class BookMarkValue {
	
	int id;
	String bookId;
	String comment;
	long time;
	String name;
	int numberOfPage;
	int type;
	
	public BookMarkValue(int id, String bookId,String name, String comment, long time,
			 int numberOfPage,int type) {
		this.id = id;
		this.bookId = bookId;
		this.comment = comment;
		this.time = time;
		this.name = name;
		this.numberOfPage = numberOfPage;
		this.type = type;
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumberOfPage() {
		return numberOfPage;
	}
	public void setNumberOfPage(int numberOfPage) {
		this.numberOfPage = numberOfPage;
	}

	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
}	
