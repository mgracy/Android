/**
 * 
 */
package com.tujk.istudy.vo;

/**
 * title  : SdCardInfo.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-6-28
 */
public class SdCardInfo {

	private String cardPath;
	private long totalStorage;
	private long availableStorage;
	
	public SdCardInfo(){}
	
	public SdCardInfo(String cardPath, long totalStorage, long availableStorage) {
		this.cardPath = cardPath;
		this.totalStorage = totalStorage;
		this.availableStorage = availableStorage;
	}
	
	public String getCardPath() {
		return cardPath;
	}
	public void setCardPath(String cardPath) {
		this.cardPath = cardPath;
	}
	public long getTotalStorage() {
		return totalStorage;
	}
	public void setTotalStorage(long totalStorage) {
		this.totalStorage = totalStorage;
	}
	public long getAvailableStorage() {
		return availableStorage;
	}
	public void setAvailableStorage(long availableStorage) {
		this.availableStorage = availableStorage;
	}
}
