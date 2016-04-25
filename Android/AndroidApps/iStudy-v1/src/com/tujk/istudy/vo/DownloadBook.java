/**
 * 
 */
package com.tujk.istudy.vo;

/**
 * title  : Book.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-5-27
 */
public class DownloadBook {
	
	String id;
	String poster;
	String title;
	String desc;
	int downloadProgress;
	String time;
	String subTitle;
	boolean isSubBook;
	boolean cancelDownload = false;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getDownloadProgress() {
		return downloadProgress;
	}
	public void setDownloadProgress(int downloadProgress) {
		this.downloadProgress = downloadProgress;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	public boolean isSubBook() {
		return isSubBook;
	}
	public void setSubBook(boolean isSubBook) {
		this.isSubBook = isSubBook;
	}
	public boolean isCancelDownload() {
		return cancelDownload;
	}
	public void setCancelDownload(boolean cancelDownload) {
		this.cancelDownload = cancelDownload;
	}
	
}
