package com.tujk.istudy.pdf;

import android.graphics.Bitmap;

/**
 * title  : BitmapCacheValue.java
 * desc   : 
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-4-26
 */
public class BitmapCacheValue {
	public Bitmap bitmap;
	/* public long millisAdded; */
	public long millisAccessed;
	public long priority;
	
	public BitmapCacheValue(Bitmap bitmap, long millisAdded, long priority) {
		this.bitmap = bitmap;
		/* this.millisAdded = millisAdded; */
		this.millisAccessed = millisAdded;
		this.priority = priority;
	}
}
