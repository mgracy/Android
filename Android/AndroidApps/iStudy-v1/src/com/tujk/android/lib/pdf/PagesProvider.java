package com.tujk.android.lib.pdf;

import java.util.Collection;

import android.graphics.Bitmap;

/**
 * 
 * title  : PagesProvider.java
 * desc   : Provide content of pages rendered by PagesView.
 * author : tujiakuan
 * QQ     : 44822331
 * email  : jiakuantu@gmail.com
 * date   : 2013-4-26
 */
public abstract class PagesProvider {

	/**
	 * Get page image tile for drawing.
	 */
	public abstract Bitmap getPageBitmap(Tile tile);
	
	/**
	 * Get page count.
	 * This cannot change between executions - PagesView assumes (for now) that docuement doesn't change.
	 */
	public abstract int getPageCount();
	
	/**
	 * Get page sizes.
	 * This cannot change between executions - PagesView assumes (for now) that docuement doesn't change.
	 */
	public abstract int[][] getPageSizes();
	
	/**
	 * Set notify target.
	 * Usually page rendering takes some time. If image cannot be provided
	 * immediately, provider may return null.
	 * Then it's up to provider to notify view that requested image has arrived
	 * and is ready to be drawn.
	 * This function, if overridden, can be used to inform provider who
	 * should be notifed.
	 * Default implementation does nothing. 
	 */
	public void setOnImageRenderedListener(OnImageRenderedListener listener) {
		/* to be overridden when needed */
	}
	
	public abstract void setVisibleTiles(Collection<Tile> tiles);

	public abstract float getRenderAhead();
}
