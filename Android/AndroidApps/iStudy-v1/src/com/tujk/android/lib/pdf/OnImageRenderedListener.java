package com.tujk.android.lib.pdf;

import java.util.Map;

import android.graphics.Bitmap;

/**
 * title  : OnImageRenderedListener.java
 * desc   : Allow renderer to notify view that new bitmaps are ready.
 * Implemented by PagesView.
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-4-26
 */
public interface OnImageRenderedListener {
	void onImagesRendered(Map<Tile,Bitmap> renderedImages);
	void onRenderingException(RenderingException reason);
}
